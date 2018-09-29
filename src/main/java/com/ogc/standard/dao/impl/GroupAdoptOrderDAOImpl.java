package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupAdoptOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GroupAdoptOrder;

@Repository("groupAdoptOrderDAOImpl")
public class GroupAdoptOrderDAOImpl extends AMybatisTemplate implements
        IGroupAdoptOrderDAO {

    @Override
    public int insert(GroupAdoptOrder data) {
        return super.insert(NAMESPACE.concat("insert_groupAdoptOrder"), data);
    }

    @Override
    public int insertFirst(GroupAdoptOrder data) {
        return super.insert(NAMESPACE.concat("insert_firstGroupAdoptOrder"),
            data);
    }

    @Override
    public int insertUnFirst(GroupAdoptOrder data) {
        return super.insert(NAMESPACE.concat("insert_unFirstgroupAdoptOrder"),
            data);
    }

    @Override
    public int delete(GroupAdoptOrder data) {
        return super.delete(NAMESPACE.concat("delete_groupAdoptOrder"), data);
    }

    @Override
    public GroupAdoptOrder select(GroupAdoptOrder condition) {
        return super.select(NAMESPACE.concat("select_groupAdoptOrder"),
            condition, GroupAdoptOrder.class);
    }

    @Override
    public long selectTotalCount(GroupAdoptOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_groupAdoptOrder_count"), condition);
    }

    @Override
    public List<GroupAdoptOrder> selectList(GroupAdoptOrder condition) {
        return super.selectList(NAMESPACE.concat("select_groupAdoptOrder"),
            condition, GroupAdoptOrder.class);
    }

    @Override
    public List<GroupAdoptOrder> selectList(GroupAdoptOrder condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_groupAdoptOrder"),
            start, count, condition, GroupAdoptOrder.class);
    }

    @Override
    public int update(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_groupAdoptOrder"), data);
    }

    @Override
    public void updateCancelGroupAdoptOrder(GroupAdoptOrder data) {
        super.update(NAMESPACE.concat("update_cancelGroupAdoptOrder"), data);
    }

    @Override
    public void updatePayGroupAdoptOrder(GroupAdoptOrder data) {
        super.update(NAMESPACE.concat("update_payGroupAdoptOrder"), data);
    }

}
