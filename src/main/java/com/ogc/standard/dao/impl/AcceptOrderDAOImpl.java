package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAcceptOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AcceptOrder;

@Repository("acceptOrderDAOImpl")
public class AcceptOrderDAOImpl extends AMybatisTemplate
        implements IAcceptOrderDAO {

    @Override
    public int insert(AcceptOrder data) {
        return super.insert(NAMESPACE.concat("insert_coinAcceptOrder"), data);
    }

    @Override
    public int delete(AcceptOrder data) {
        return super.delete(NAMESPACE.concat("delete_coinAcceptOrder"), data);
    }

    @Override
    public AcceptOrder select(AcceptOrder condition) {
        return super.select(NAMESPACE.concat("select_coinAcceptOrder"),
            condition, AcceptOrder.class);
    }

    @Override
    public long selectTotalCount(AcceptOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_coinAcceptOrder_count"), condition);
    }

    @Override
    public List<AcceptOrder> selectList(AcceptOrder condition) {
        return super.selectList(NAMESPACE.concat("select_coinAcceptOrder"),
            condition, AcceptOrder.class);
    }

    @Override
    public List<AcceptOrder> selectList(AcceptOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_coinAcceptOrder"),
            start, count, condition, AcceptOrder.class);
    }

    @Override
    public int updateCancel(AcceptOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_cancel"), tradeOrder);
    }

    @Override
    public int updateMarkPay(AcceptOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_markPay"), tradeOrder);
    }

}
