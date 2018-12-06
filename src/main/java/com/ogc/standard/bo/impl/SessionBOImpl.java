/**
 * @Title SessionBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:39:35 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISessionBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ISessionDAO;
import com.ogc.standard.domain.Session;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:39:35 
 * @history:
 */
@Component
public class SessionBOImpl extends PaginableBOImpl<Session>
        implements ISessionBO {

    @Autowired
    private ISessionDAO sessionDAO;

    @Override
    public Session saveSession(String type, String user1, String user2) {
        Session data = new Session();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Session.getCode());
        data.setCode(code);
        data.setType(type);
        data.setUser1(user1);
        data.setUser2(user2);
        data.setCreateDatetime(new Date());
        data.setUser1UnreadSum(Long.valueOf(0));
        data.setUser2UnreadSum(Long.valueOf(0));
        sessionDAO.insert(data);
        return data;
    }

    @Override
    public void refreshUnreadSum1(Session data, Long sum) {
        data.setUser1UnreadSum(sum);
        sessionDAO.updateUnreadSum1(data);
    }

    @Override
    public void refreshUnreadSum2(Session data, Long sum) {
        data.setUser2UnreadSum(sum);
        sessionDAO.updateUnreadSum2(data);
    }

    @Override
    public Session getSessionExist(String type, String user1, String user2) {
        Session condition = new Session();
        condition.setUser1(user1);
        condition.setUser2(user2);
        condition.setType(type);
        List<Session> sessions = sessionDAO.selectList(condition);
        if (CollectionUtils.isEmpty(sessions)) {
            return null;
        }
        return sessions.get(0);
    }

    @Override
    public List<Session> queryUnread(String user1, String user2) {
        Session condition = new Session();
        condition.setUser1(user1);
        condition.setUser2(user2);
        condition.setExistsUnread(EBoolean.YES.getCode());

        return sessionDAO.selectList(condition);
    }

    @Override
    public List<Session> querySessionList(Session condition) {
        return sessionDAO.selectList(condition);
    }

    @Override
    public Session getSession(String code) {
        Session condition = new Session();
        condition.setCode(code);
        Session data = sessionDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "该会话不存在");
        }
        return data;
    }

    @Override
    public void clearUser1UnreadSum(String user1, String code) {
        Session session = new Session();
        session.setUser1(user1);
        session.setCode(code);
        sessionDAO.updateClearUser1UnreadSum(session);
    }

    @Override
    public void clearUser2UnreadSum(String user2, String code) {
        Session session = new Session();
        session.setUser2(user2);
        session.setCode(code);
        sessionDAO.updateClearUser2UnreadSum(session);
    }

}
