package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuMatchResult;
import com.ogc.standard.domain.SimuOrder;

public interface ISimuMatchResultBO extends IPaginableBO<SimuMatchResult> {

    public void doSimuMatchResult(SimuOrder bidsOrder, SimuOrder asksOrder);

}
