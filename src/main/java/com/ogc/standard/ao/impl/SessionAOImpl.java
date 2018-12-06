/**
 * @Title SessionAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午2:07:22 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISessionAO;
import com.ogc.standard.bo.ISessionBO;
import com.ogc.standard.bo.ISessionMessageBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Session;
import com.ogc.standard.domain.SessionMessage;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629788Res;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESessionMessageStatus;
import com.ogc.standard.enums.ESessionType;
import com.ogc.standard.enums.ESysUser;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午2:07:22 
 * @history:
 */
@Service
public class SessionAOImpl implements ISessionAO {

    @Autowired
    private ISessionBO sessionBO;

    @Autowired
    private ISessionMessageBO sessionMessageBO;

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public void send(String user1, String user2, String content, String type) {
        if (ESessionType.service.equals(type)) {
            user2 = ESysUser.SYS_USER.getCode();
        }
        // 判断是否有该会话
        Session session = sessionBO.getSessionExist(type, user1, user2);
        // 没有则新增一个
        if (null == session) {
            session = sessionBO.saveSession(type, user1, user2);
        }
        // 新增消息
        sessionMessageBO.saveMessage(session.getCode(), user1, content);
        // 未读消息加一
        sessionBO.refreshUnreadSum2(session, session.getUser2UnreadSum() + 1);
    }

    @Override
    @Transactional
    public void replay(String code, String userId, String content) {
        // 会话判断存在
        Session session = sessionBO.getSession(code);
        // 新增消息
        sessionMessageBO.saveMessage(code, userId, content);

        // 未读消息加一
        if (session.getUser1().equals(userId)) {
            sessionBO.refreshUnreadSum1(session, Long.valueOf(0));
            sessionBO.refreshUnreadSum2(session,
                session.getUser2UnreadSum() + 1);
        } else if (session.getUser2().equals(userId)) {
            sessionBO.refreshUnreadSum2(session, Long.valueOf(0));
            sessionBO.refreshUnreadSum1(session,
                session.getUser1UnreadSum() + 1);
        }

    }

    @Override
    @Transactional
    public void read(String code, String userId) {
        String toUser = null;
        // 会话判断存在
        Session session = sessionBO.getSession(code);
        // 会话未读数归零
        if (session.getUser1().equals(userId)) {
            sessionBO.refreshUnreadSum1(session, Long.valueOf(0));
            toUser = session.getUser2();
        } else if (session.getUser2().equals(userId)) {
            sessionBO.refreshUnreadSum2(session, Long.valueOf(0));
            toUser = session.getUser1();
        } else {
            throw new BizException("xn0000", "你未参与会话，无法阅读");
        }
        // 消息状态更改
        List<SessionMessage> messageList = sessionMessageBO
            .querySessionMessages(code, toUser,
                ESessionMessageStatus.unread.getCode());
        for (SessionMessage message : messageList) {
            sessionMessageBO.refreshRead(message);
        }

    }

    @Override
    public XN629788Res existsUnread(String user1, String user2) {
        List<Session> unread = sessionBO.queryUnread(user1, user2);

        XN629788Res res = new XN629788Res(EBoolean.NO.getCode());

        if (CollectionUtils.isNotEmpty(unread)) {
            res = new XN629788Res(EBoolean.YES.getCode());
        }

        return res;
    }

    @Override
    public void clearUserUnreadSum(String user1, String user2, String code) {
        if (StringUtils.isNotBlank(user1)) {
            sessionBO.clearUser1UnreadSum(user1, code);
        }
        if (StringUtils.isNotBlank(user2)) {
            sessionBO.clearUser2UnreadSum(user2, code);
        }
    }

    @Override
    public Paginable<Session> querySessionPage(int start, int limit,
            Session condition) {
        Paginable<Session> page = sessionBO.getPaginable(start, limit,
            condition);
        for (Session session : page.getList()) {
            initSession(session);
        }
        return page;
    }

    @Override
    public List<Session> querySessionList(Session condition) {
        List<Session> sessions = sessionBO.querySessionList(condition);
        for (Session session : sessions) {
            initSession(session);
        }
        return sessions;
    }

    @Override
    public Session getSession(String code) {
        Session session = sessionBO.getSession(code);
        initSession(session);
        return session;
    }

    private void initSession(Session session) {
        List<SessionMessage> messages = sessionMessageBO
            .querySessionMessages(session.getCode(), null, null);
        session.setMessageList(messages);
        User user1 = userBO.getUser(session.getUser1());
        session.setUser1Nickname(user1.getNickname());
        session.setMobile(user1.getMobile());

        // User user2 = userBO.getUser(session.getUser2());
        // session.setUser2Nickname(user2.getNickname());
    }

}
