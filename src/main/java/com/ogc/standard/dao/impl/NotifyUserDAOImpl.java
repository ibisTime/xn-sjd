package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.INotifyUserDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.NotifyUser;

@Repository("notifyUserDAOImpl")
public class NotifyUserDAOImpl extends AMybatisTemplate
        implements INotifyUserDAO {

    @Override
    public int insert(NotifyUser data) {
        return super.insert(NAMESPACE.concat("insert_notifyUser"), data);
    }

    @Override
    public int delete(NotifyUser data) {
        return super.delete(NAMESPACE.concat("delete_notifyUser"), data);
    }

    @Override
    public int updateNotifyUser(NotifyUser notifyUser) {
        return super.update(NAMESPACE.concat("update_notifyUser"), notifyUser);
    }

    @Override
    public NotifyUser select(NotifyUser condition) {
        return super.select(NAMESPACE.concat("select_notifyUser"), condition,
            NotifyUser.class);
    }

    @Override
    public long selectTotalCount(NotifyUser condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_notifyUser_count"), condition);
    }

    @Override
    public List<NotifyUser> selectList(NotifyUser condition) {
        return super.selectList(NAMESPACE.concat("select_notifyUser"),
            condition, NotifyUser.class);
    }

    @Override
    public List<NotifyUser> selectList(NotifyUser condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_notifyUser"), start,
            count, condition, NotifyUser.class);
    }

}
