package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ICoinAcceptOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.CoinAcceptOrder;

@Repository("coinAcceptOrderDAOImpl")
public class CoinAcceptOrderDAOImpl extends AMybatisTemplate
        implements ICoinAcceptOrderDAO {

    @Override
    public int insert(CoinAcceptOrder data) {
        return super.insert(NAMESPACE.concat("insert_coinAcceptOrder"), data);
    }

    @Override
    public int delete(CoinAcceptOrder data) {
        return super.delete(NAMESPACE.concat("delete_coinAcceptOrder"), data);
    }

    @Override
    public CoinAcceptOrder select(CoinAcceptOrder condition) {
        return super.select(NAMESPACE.concat("select_coinAcceptOrder"),
            condition, CoinAcceptOrder.class);
    }

    @Override
    public long selectTotalCount(CoinAcceptOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_coinAcceptOrder_count"), condition);
    }

    @Override
    public List<CoinAcceptOrder> selectList(CoinAcceptOrder condition) {
        return super.selectList(NAMESPACE.concat("select_coinAcceptOrder"),
            condition, CoinAcceptOrder.class);
    }

    @Override
    public List<CoinAcceptOrder> selectList(CoinAcceptOrder condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_coinAcceptOrder"),
            start, count, condition, CoinAcceptOrder.class);
    }

    @Override
    public int updateCancel(CoinAcceptOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_cancel"), tradeOrder);
    }

    @Override
    public int updateMarkPay(CoinAcceptOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_markPay"), tradeOrder);
    }

}
