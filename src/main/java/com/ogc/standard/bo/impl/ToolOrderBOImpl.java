package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IToolOrderDAO;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EToolOrderStatus;
import com.ogc.standard.exception.BizException;

@Component
public class ToolOrderBOImpl extends PaginableBOImpl<ToolOrder>
        implements IToolOrderBO {

    @Autowired
    private IToolOrderDAO toolOrderDAO;

    @Override
    public String saveToolOrder(Tool tool, User user) {
        ToolOrder data = new ToolOrder();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TOOL_ORDER.getCode());
        data.setCode(code);
        data.setToolCode(tool.getCode());
        data.setToolName(tool.getName());
        data.setToolPic(tool.getPic());
        data.setPrice(tool.getPrice());
        data.setValidityTerm(tool.getValidityTerm());

        data.setUserId(user.getUserId());
        data.setCreateDatetime(new Date());
        data.setStatus(EToolOrderStatus.TO_USE.getCode());

        toolOrderDAO.insert(data);
        return code;
    }

    @Override
    public List<ToolOrder> queryToolOrderList(ToolOrder condition) {
        return toolOrderDAO.selectList(condition);
    }

    @Override
    public ToolOrder getToolOrder(String code) {
        ToolOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            ToolOrder condition = new ToolOrder();
            condition.setCode(code);
            data = toolOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "道具订单不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshStatus(ToolOrder toolOrder) {
        toolOrder.setStatus(EToolOrderStatus.USED.getCode());
        toolOrderDAO.updateStatus(toolOrder);

    }
}
