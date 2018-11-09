/**
 * @Title SessionDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:06:36 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISessionDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Session;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:06:36 
 * @history:
 */
@Repository("sessionDAOImpl")
public class SessionDAOImpl extends AMybatisTemplate implements ISessionDAO {

    @Override
    public int insert(Session data) {
        return super.insert(NAMESPACE.concat("insert_session"), data);
    }

    @Override
    public int delete(Session data) {
        return 0;
    }

    @Override
    public Session select(Session condition) {
        return super.select(NAMESPACE.concat("select_session"), condition,
            Session.class);
    }

    @Override
    public long selectTotalCount(Session condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_session_count"),
            condition);
    }

    @Override
    public List<Session> selectList(Session condition) {
        return super.selectList(NAMESPACE.concat("select_session"), condition,
            Session.class);
    }

    @Override
    public List<Session> selectList(Session condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_session"), start,
            count, condition, Session.class);
    }

    @Override
    public int updateUnreadSum1(Session data) {
        return super.update(NAMESPACE.concat("update_unread_sum1"), data);
    }

    @Override
    public int updateUnreadSum2(Session data) {
        return super.update(NAMESPACE.concat("update_unread_sum2"), data);
    }

}
