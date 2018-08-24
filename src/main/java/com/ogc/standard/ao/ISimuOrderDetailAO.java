package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrderDetail;

public interface ISimuOrderDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "create_datetime";

    public Paginable<SimuOrderDetail> querySimuOrderDetailPage(int start,
            int limit, SimuOrderDetail condition);

    public List<SimuOrderDetail> querySimuOrderDetailList(
            SimuOrderDetail condition);

    public SimuOrderDetail getSimuOrderDetail(String code);

}
