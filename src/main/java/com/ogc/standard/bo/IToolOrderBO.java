package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.ToolOrder;

public interface IToolOrderBO extends IPaginableBO<ToolOrder> {

    public boolean isToolOrderExist(String code);

    public String saveToolOrder(ToolOrder data);

    public List<ToolOrder> queryToolOrderList(ToolOrder condition);

    public ToolOrder getToolOrder(String code);

}
