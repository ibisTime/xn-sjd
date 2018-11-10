package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGroupOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GroupOrder;

@Repository("groupOrderDAOImpl")
public class GroupOrderDAOImpl extends AMybatisTemplate
        implements IGroupOrderDAO {

    @Override
    public int insert(GroupOrder data) {
        return super.insert(NAMESPACE.concat("insert_groupOrder"), data);
    }

    @Override
    public int delete(GroupOrder data) {
        return super.delete(NAMESPACE.concat("delete_groupOrder"), data);
    }

    @Override
    public int updateCancelOrder(GroupOrder data) {
        return super.update(NAMESPACE.concat("update_CalcelGroupOrder"), data);
    }

    @Override
    public int updatePayYueSuccess(GroupOrder data) {
        return super.update(NAMESPACE.concat("update_payYueSuccess"), data);
    }

    @Override
    public int updatePayGroup(GroupOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updatePaySuccess(GroupOrder data) {
        return super.update(NAMESPACE.concat("update_paySuccess"), data);
    }

    @Override
    public GroupOrder select(GroupOrder condition) {
        return super.select(NAMESPACE.concat("select_groupOrder"), condition,
            GroupOrder.class);
    }

    @Override
    public long selectTotalCount(GroupOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_groupOrder_count"), condition);
    }

    @Override
    public List<GroupOrder> selectList(GroupOrder condition) {
        return super.selectList(NAMESPACE.concat("select_groupOrder"),
            condition, GroupOrder.class);
    }

    @Override
    public List<GroupOrder> selectList(GroupOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_groupOrder"), start,
            count, condition, GroupOrder.class);
    }

}
