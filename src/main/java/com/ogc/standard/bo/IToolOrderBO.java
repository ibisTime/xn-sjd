package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.User;

public interface IToolOrderBO extends IPaginableBO<ToolOrder> {

    public String saveToolOrder(Tool tool, User user);

    public List<ToolOrder> queryToolOrderList(ToolOrder condition);

    public ToolOrder getToolOrder(String code);

    public void refreshStatus(ToolOrder toolOrder);

}
