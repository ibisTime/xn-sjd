package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuOrder;

public interface ISimuOrderBO extends IPaginableBO<SimuOrder> {

    public boolean isSimuOrderExist(String code);

    public void saveSimuOrder(SimuOrder data);

    public void tradeSuccess(SimuOrder data);

    public int cancel(SimuOrder data);

    public List<SimuOrder> querySimuOrderList(SimuOrder condition);

    public SimuOrder getSimuOrder(String code);

}
