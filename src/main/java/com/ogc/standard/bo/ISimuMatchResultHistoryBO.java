package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuMatchResultHistory;

public interface ISimuMatchResultHistoryBO
        extends IPaginableBO<SimuMatchResultHistory> {

    public String saveSimuMatchResultHistory(SimuMatchResultHistory data);

    public List<SimuMatchResultHistory> querySimuMatchResultHistoryList(
            SimuMatchResultHistory condition);

}
