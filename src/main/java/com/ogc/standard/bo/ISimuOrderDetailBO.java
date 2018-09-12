package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.domain.SimuOrderDetail;

public interface ISimuOrderDetailBO extends IPaginableBO<SimuOrderDetail> {

    public SimuOrderDetail saveSimuOrderDetail(SimuOrder simuOrder,
            BigDecimal tradedPrice, BigDecimal tradedCount,
            BigDecimal tradedAmount, BigDecimal tradedFee);

    public List<SimuOrderDetail> querySimuOrderDetailList(
            SimuOrderDetail condition);

    public SimuOrderDetail getSimuOrderDetail(String code);

}
