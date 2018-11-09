/**
 * @Title SessionMessageDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:14:09 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISessionMessageDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SessionMessage;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:14:09 
 * @history:
 */
@Repository("sessionMessageDAOImpl")
public class SessionMessageDAOImpl extends AMybatisTemplate implements
        ISessionMessageDAO {

    @Override
    public int insert(SessionMessage data) {
        return super.insert(NAMESPACE.concat("insert_session_message"), data);
    }

    @Override
    public int delete(SessionMessage data) {
        return 0;
    }

    @Override
    public SessionMessage select(SessionMessage condition) {
        return super.select(NAMESPACE.concat("select_session_message"),
            condition, SessionMessage.class);
    }

    @Override
    public long selectTotalCount(SessionMessage condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_session_message_count"), condition);
    }

    @Override
    public List<SessionMessage> selectList(SessionMessage condition) {
        return super.selectList(NAMESPACE.concat("select_session_message"),
            condition, SessionMessage.class);
    }

    @Override
    public List<SessionMessage> selectList(SessionMessage condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_session_message"),
            start, count, condition, SessionMessage.class);
    }

    @Override
    public int updateStatus(SessionMessage data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
