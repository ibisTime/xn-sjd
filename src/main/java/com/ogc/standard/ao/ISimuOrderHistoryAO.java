package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrderHistory;

public interface ISimuOrderHistoryAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<SimuOrderHistory> querySimuOrderHistoryPage(int start,
            int limit, SimuOrderHistory condition);

    public List<SimuOrderHistory> querySimuOrderHistoryList(
            SimuOrderHistory condition);

}
