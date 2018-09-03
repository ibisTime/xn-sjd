package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.SimuOrderDetail;

public interface ISimuOrderDetailBO extends IPaginableBO<SimuOrderDetail> {

    public String saveSimuOrderDetail(SimuOrderDetail data);

    public List<SimuOrderDetail> querySimuOrderDetailList(
            SimuOrderDetail condition);

    public SimuOrderDetail getSimuOrderDetail(String code);

}
