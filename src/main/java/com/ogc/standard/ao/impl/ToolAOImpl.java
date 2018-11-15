package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IToolAO;
import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.IToolOrderBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EToolOrderStatus;
import com.ogc.standard.enums.EToolStatus;
import com.ogc.standard.enums.EToolType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ToolAOImpl implements IToolAO {

    @Autowired
    private IToolBO toolBO;

    @Autowired
    private IToolOrderBO toolOrderBO;

    @Override
    public void editTool(XN629502Req req) {

        Tool data = toolBO.getTool(req.getCode());

        if (EToolStatus.UP.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可修改状态");
        }

        toolBO.refreshTool(data, req);
    }

    @Override
    public void putUp(String code, String orderNo, String updater,
            String remark) {

        // 是否可上架
        Tool tool = toolBO.getTool(code);
        if (EToolStatus.UP.getCode().equals(tool.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可上架状态");
        }

        tool.setStatus(EToolStatus.UP.getCode());
        toolBO.refreshUp(tool, orderNo, updater, remark);
    }

    @Override
    public void putDown(String code, String updater, String remark) {

        // 是否可上架
        Tool tool = toolBO.getTool(code);
        if (EToolStatus.DOWN.getCode().equals(tool.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可下架状态");
        }

        tool.setStatus(EToolStatus.DOWN.getCode());
        toolBO.refreshDown(tool, updater, remark);
    }

    @Override
    public Paginable<Tool> queryToolPage(int start, int limit, Tool condition) {
        Paginable<Tool> page = toolBO.getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {

            for (Tool tool : page.getList()) {
                init(condition, tool);
            }

        }

        return page;
    }

    @Override
    public List<Tool> queryToolList(Tool condition) {
        List<Tool> list = toolBO.queryToolList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Tool tool : list) {
                init(condition, tool);
            }
        }

        return list;
    }

    @Override
    public Tool getTool(String code) {
        Tool tool = toolBO.getTool(code);

        // 描述
        if (EToolType.SHIELD.getCode().equals(tool.getType())) {
            String description = tool.getDescription();
            description = description.replace("*",
                tool.getValidityTerm().toString());
            tool.setDescription(description);
        }

        return tool;
    }

    private void init(Tool condition, Tool tool) {
        // 根据购买记录查询用户是否购买道具
        if (StringUtils.isNotBlank(condition.getUserId())) {

            if (CollectionUtils.isNotEmpty(
                toolOrderBO.queryUserToolOrderList(condition.getUserId(),
                    tool.getCode(), EToolOrderStatus.TO_USE.getCode()))) {
                tool.setIsBuy(EBoolean.YES.getCode());
            } else {
                tool.setIsBuy(EBoolean.NO.getCode());
            }
        }
    }

}
