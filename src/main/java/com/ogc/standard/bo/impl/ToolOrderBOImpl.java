package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IToolOrderDAO;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class ToolOrderBOImpl extends PaginableBOImpl<ToolOrder>
        implements IToolOrderBO {

    @Autowired
    private IToolOrderDAO toolOrderDAO;

    @Override
    public boolean isToolOrderExist(String code) {
        ToolOrder condition = new ToolOrder();
        condition.setCode(code);
        if (toolOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveToolOrder(ToolOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.TOOL_ORDER.getCode());
            data.setCode(code);
            toolOrderDAO.insert(data);
        }
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
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }
}
