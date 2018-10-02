package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ToolOrder;

public interface IToolOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addToolOrder(String toolCode, String userId);

    public Paginable<ToolOrder> queryToolOrderPage(int start, int limit,
            ToolOrder condition);

    public List<ToolOrder> queryToolOrderList(ToolOrder condition);

    public ToolOrder getToolOrder(String code);

}
