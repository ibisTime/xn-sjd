package com.ogc.standard.dao.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ogc.standard.enums.ETradeOrderStatus;
import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ITradeOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.TradeOrder;

@Repository("tradeOrderDAOImpl")
public class TradeOrderDAOImpl extends AMybatisTemplate implements
        ITradeOrderDAO {

    @Override
    public int insert(TradeOrder data) {
        return super.insert(NAMESPACE.concat("insert_tradeOrder"), data);
    }

    @Override
    public int delete(TradeOrder data) {
        return super.delete(NAMESPACE.concat("delete_tradeOrder"), data);
    }

    @Override
    public TradeOrder select(TradeOrder condition) {
        return super.select(NAMESPACE.concat("select_tradeOrder"), condition,
                TradeOrder.class);
    }

    @Override
    public long selectTotalCount(TradeOrder condition) {
        return super.selectTotalCount(
                NAMESPACE.concat("select_tradeOrder_count"), condition);
    }

    @Override
    public List<TradeOrder> selectList(TradeOrder condition) {
        return super.selectList(NAMESPACE.concat("select_tradeOrder"),
                condition, TradeOrder.class);
    }

    @Override
    public List<TradeOrder> selectList(TradeOrder condition, int start,
                                       int count) {
        return super.selectList(NAMESPACE.concat("select_tradeOrder"), start,
                count, condition, TradeOrder.class);
    }

    @Override
    public int updateCancel(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_cancel"), tradeOrder);
    }

    @Override
    public int updateMarkPay(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_markPay"), tradeOrder);
    }

    @Override
    public int updateRelease(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_release"), tradeOrder);
    }

    @Override
    public int updateBsComment(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_bsComment"), tradeOrder);
    }

    @Override
    public int updateSbComment(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_sbComment"), tradeOrder);
    }

    @Override
    public int updateArbitrate(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_arbitrate"), tradeOrder);
    }

    @Override
    public int updateRevokePay(TradeOrder tradeOrder) {
        return super.update(NAMESPACE.concat("update_revokePay"), tradeOrder);
    }

    @Override
    public int updateSubmit(TradeOrder data) {
        return super.update(NAMESPACE.concat("update_submit"), data);
    }

    @Override
    public void deleteByAdsCodeAndStatus(TradeOrder data) {

        super.delete(NAMESPACE.concat("deleteByAdsCodeAndStatus"), data);

    }

    @Override
    public BigDecimal selectedTotalTradeCount(TradeOrder data) {

        return super.select(NAMESPACE.concat("selectedTotalTradeCount"), data, BigDecimal.class);

    }
}
