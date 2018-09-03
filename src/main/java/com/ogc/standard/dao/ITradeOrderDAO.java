package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.TradeOrder;

import java.math.BigDecimal;

public interface ITradeOrderDAO extends IBaseDAO<TradeOrder> {

    String NAMESPACE = ITradeOrderDAO.class.getName().concat(".");

    public int updateCancel(TradeOrder tradeOrder);

    public int updateMarkPay(TradeOrder tradeOrder);

    public int updateRelease(TradeOrder tradeOrder);

    public int updateBsComment(TradeOrder tradeOrder);

    public int updateSbComment(TradeOrder tradeOrder);

    public int updateArbitrate(TradeOrder tradeOrder);

    public int updateRevokePay(TradeOrder tradeOrder);

    public int updateSubmit(TradeOrder data);

    public void deleteByAdsCodeAndStatus(TradeOrder data);

    public BigDecimal selectedTotalTradeCount(TradeOrder data);

}
