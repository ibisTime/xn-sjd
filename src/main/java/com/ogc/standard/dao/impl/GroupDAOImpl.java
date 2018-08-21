package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Group;

@Repository("groupDAOImpl")
public class GroupDAOImpl extends AMybatisTemplate implements IGroupDAO {

    @Override
    public int insert(Group data) {
        return super.insert(NAMESPACE.concat("insert_group"), data);
    }

    @Override
    public int delete(Group data) {
        return super.delete(NAMESPACE.concat("delete_group"), data);
    }

    @Override
    public Group select(Group condition) {
        return super.select(NAMESPACE.concat("select_group"), condition,
            Group.class);
    }

    @Override
    public long selectTotalCount(Group condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_group_count"),
            condition);
    }

    @Override
    public List<Group> selectList(Group condition) {
        return super.selectList(NAMESPACE.concat("select_group"), condition,
            Group.class);
    }

    @Override
    public List<Group> selectList(Group condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_group"), start, count,
            condition, Group.class);
    }

    @Override
    public int updateGroupFollowNumber(Group data) {
        return super.update(NAMESPACE.concat("delete_group"), data);
    }

}
