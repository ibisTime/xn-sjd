package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupAdoptOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GroupAdoptOrder;

@Repository("groupAdoptOrderDAOImpl")
public class GroupAdoptOrderDAOImpl extends AMybatisTemplate
        implements IGroupAdoptOrderDAO {

    @Override
    public int insert(GroupAdoptOrder data) {
        return 0;
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
    public void updateCancelGroupAdoptOrder(GroupAdoptOrder data) {
        super.update(NAMESPACE.concat("update_cancelGroupAdoptOrder"), data);
    }

    @Override
    public void updatePayYueSuccess(GroupAdoptOrder data) {
        super.update(NAMESPACE.concat("update_payYueSuccess"), data);
    }

    @Override
    public int updatePayGroup(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updatePaySuccess(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_paySuccess"), data);
    }

    @Override
    public int updateFullOrderById(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_fullOrderById"), data);
    }

    @Override
    public int updateUnFullOrderById(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_unFullOrderById"), data);
    }

    @Override
    public int updateFullOrder(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_fullOrder"), data);
    }

    @Override
    public int updateUnFullOrder(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_unFullOrder"), data);
    }

    @Override
    public int updateStartAdopt(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_startAdopt"), data);
    }

    @Override
    public int updateEndAdopt(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_endAdopt"), data);
    }

    @Override
    public int updateSettleStatus(GroupAdoptOrder data) {
        return super.update(NAMESPACE.concat("update_settleStatus"), data);
    }

    @Override
    public int delete(GroupAdoptOrder data) {
        return 0;
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

}
