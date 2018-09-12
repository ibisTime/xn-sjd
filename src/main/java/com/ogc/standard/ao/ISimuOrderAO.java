package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.dto.req.XN650050Req;

public interface ISimuOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "create_datetime";

    static final String ORDER_COLUMN_PRICE = "price";

    // 挂单
    public String submit(XN650050Req req);

    // 撤单
    public void cancel(String code);

    public Paginable<SimuOrder> querySimuOrderPage(int start, int limit,
            SimuOrder condition);

    public List<SimuOrder> querySimuOrderList(SimuOrder condition);

    public SimuOrder getSimuOrder(String code);

}
