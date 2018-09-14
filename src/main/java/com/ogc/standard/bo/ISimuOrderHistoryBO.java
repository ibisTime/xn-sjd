package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.domain.SimuOrderHistory;

public interface ISimuOrderHistoryBO extends IPaginableBO<SimuOrderHistory> {

    public String saveSimuOrderHistory(SimuOrder data);

    public List<SimuOrderHistory> querySimuOrderHistoryList(
            SimuOrderHistory condition);

    public SimuOrderHistory getSimuOrderHistory(String code);
}
