package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.User;

@Service
public class ToolOrderAOImpl implements IToolOrderAO {

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IToolBO toolBO;

    @Autowired
    private IToolOrderBO toolOrderBO;

    @Override
    public String addToolOrder(String toolCode, String userId) {
        // 验证用户
        userBO.getUser(userId);

        // 查询道具
        Tool tool = toolBO.getTool(toolCode);

        // 组装信息
        ToolOrder data = new ToolOrder();
        data.setToolCode(tool.getCode());
        data.setToolName(tool.getName());
        data.setToolPic(tool.getPic());
        data.setPrice(tool.getPrice());
        data.setValidityTerm(tool.getValidityTerm());

        data.setUserId(userId);
        data.setCreateDatetime(new Date());
        return toolOrderBO.saveToolOrder(data);
    }

    @Override
    public Paginable<ToolOrder> queryToolOrderPage(int start, int limit,
            ToolOrder condition) {

        Paginable<ToolOrder> page = toolOrderBO.getPaginable(start, limit,
            condition);

        if (null != page) {
            for (ToolOrder toolOrder : page.getList()) {
                init(toolOrder);
            }
        }

        return page;
    }

    @Override
    public List<ToolOrder> queryToolOrderList(ToolOrder condition) {

        List<ToolOrder> toolOrderList = toolOrderBO
            .queryToolOrderList(condition);

        for (ToolOrder toolOrder : toolOrderList) {
            init(toolOrder);
        }

        return toolOrderList;
    }

    @Override
    public ToolOrder getToolOrder(String code) {

        ToolOrder toolOrder = toolOrderBO.getToolOrder(code);
        init(toolOrder);

        return toolOrder;
    }

    private void init(ToolOrder toolOrder) {
        User userInfo = userBO.getUser(toolOrder.getUserId());
        toolOrder.setUserInfo(userInfo);
    }
}
