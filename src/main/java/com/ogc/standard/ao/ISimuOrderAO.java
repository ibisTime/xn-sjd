package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.dto.req.XN650050Req;

public interface ISimuOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "create_datetime";

    // 挂单
    public String submit(XN650050Req req);

    // 当前挂单的订单是否可立即成交
    public void buySuccessOrder(String code);

    // 撤单
    public void cancel(String code);

    public Paginable<SimuOrder> querySimuOrderPage(int start, int limit,
            SimuOrder condition);

    public List<SimuOrder> querySimuOrderList(SimuOrder condition);

    public SimuOrder getSimuOrder(String code);

    // 拉取买卖五档数据，并检查委托单情况
    public void doCheckDeal();

    public void buySuccess(SimuOrder simuOrder, BigDecimal tradePrice);

    public void sellSuccess(SimuOrder simuOrder, BigDecimal tradePrice);

}
