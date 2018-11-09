/**
 * @Title SessionMessageBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:50:57 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISessionMessageBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISessionMessageDAO;
import com.ogc.standard.domain.SessionMessage;
import com.ogc.standard.enums.ESessionMessageStatus;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:50:57 
 * @history:
 */
@Component
public class SessionMessageBOImpl extends PaginableBOImpl<SessionMessage>
        implements ISessionMessageBO {

    @Autowired
    private ISessionMessageDAO sessionMessageDAO;

    @Override
    public void saveMessage(String sessionCode, String userId, String content) {
        SessionMessage data = new SessionMessage();
        data.setSessionCode(sessionCode);
        data.setUserId(userId);
        data.setContent(content);
        data.setStatus(ESessionMessageStatus.unread.getCode());
        data.setCreateDatetime(new Date());
        sessionMessageDAO.insert(data);
    }

    @Override
    public void refreshRead(SessionMessage data) {
        data.setStatus(ESessionMessageStatus.read.getCode());
        sessionMessageDAO.updateStatus(data);
    }

    @Override
    public SessionMessage getMessage(Long id) {
        SessionMessage condition = new SessionMessage();
        condition.setId(id);
        SessionMessage data = sessionMessageDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "id为" + id.toString() + "的消息不存在");
        }
        return data;
    }

    @Override
    public List<SessionMessage> querySessionMessages(String sessionCode,
            String userId, String status) {
        SessionMessage condition = new SessionMessage();
        condition.setSessionCode(sessionCode);
        condition.setUserId(userId);
        condition.setStatus(status);
        List<SessionMessage> messages = sessionMessageDAO.selectList(condition);
        return messages;
    }

}
