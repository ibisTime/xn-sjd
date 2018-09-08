package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuMatchResult;
import com.ogc.standard.domain.SimuOrderDetail;

public interface ISimuMatchResultBO extends IPaginableBO<SimuMatchResult> {

    public void doSimuMatchResult(SimuOrderDetail bidsOrder,
            SimuOrderDetail asksOrder);

    public int removeSimuMatchResult(long id);

    public List<SimuMatchResult> querySimuMatchResultHistoryList(
            SimuMatchResult condition);

}
