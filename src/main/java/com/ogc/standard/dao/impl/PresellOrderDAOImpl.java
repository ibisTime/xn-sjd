package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPresellOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PresellOrder;

@Repository("presellOrderDAOImpl")
public class PresellOrderDAOImpl extends AMybatisTemplate
        implements IPresellOrderDAO {

    @Override
    public int insert(PresellOrder data) {
        return super.insert(NAMESPACE.concat("insert_presellOrder"), data);
    }

    @Override
    public int delete(PresellOrder data) {
        return super.delete(NAMESPACE.concat("delete_presellOrder"), data);
    }

    @Override
    public int updateCancelOrder(PresellOrder data) {
        return super.update(NAMESPACE.concat("update_CalcelPresellOrder"),
            data);
    }

    @Override
    public int updatePayYueSuccess(PresellOrder data) {
        return super.update(NAMESPACE.concat("update_payYueSuccess"), data);
    }

    @Override
    public int updatePayGroup(PresellOrder data) {
        return super.update(NAMESPACE.concat("update_payGroup"), data);
    }

    @Override
    public int updatePaySuccess(PresellOrder data) {
        return super.update(NAMESPACE.concat("update_paySuccess"), data);
    }

    @Override
    public int updateSettleStatus(PresellOrder data) {
        return super.update(NAMESPACE.concat("update_settle"), data);
    }

    @Override
    public PresellOrder select(PresellOrder condition) {
        return super.select(NAMESPACE.concat("select_presellOrder"), condition,
            PresellOrder.class);
    }

    @Override
    public long selectTotalCount(PresellOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_presellOrder_count"), condition);
    }

    @Override
    public List<PresellOrder> selectList(PresellOrder condition) {
        return super.selectList(NAMESPACE.concat("select_presellOrder"),
            condition, PresellOrder.class);
    }

    @Override
    public List<PresellOrder> selectList(PresellOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_presellOrder"), start,
            count, condition, PresellOrder.class);
    }

    @Override
    public List<PresellOrder> selectListByProduct(PresellOrder condition) {
        return super.selectList(
            NAMESPACE.concat("select_presellOrderByProduct"), condition,
            PresellOrder.class);
    }

}
