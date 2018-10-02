package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IToolAO;
import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;
import com.ogc.standard.enums.EToolStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ToolAOImpl implements IToolAO {

    @Autowired
    private IToolBO toolBO;

    @Override
    public int editTool(XN629502Req req) {

        Tool data = toolBO.getTool(req.getCode());

        if (!EToolStatus.UP.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可修改状态");
        }

        return toolBO.refreshTool(data, req);
    }

    @Override
    public Paginable<Tool> queryToolPage(int start, int limit, Tool condition) {
        return toolBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Tool> queryToolList(Tool condition) {
        return toolBO.queryToolList(condition);
    }

    @Override
    public Tool getTool(String code) {
        return toolBO.getTool(code);
    }

    @Override
    public void putUp(String code, String updater, String remark) {

        // 参数验证
        Tool tool = toolBO.getTool(code);

        // 是否可上架
        if (!EToolStatus.DOWN.getCode().equals(tool.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可上架状态");
        }

        toolBO.refreshStatus(tool, updater, remark);
    }

    @Override
    public void getDown(String code, String updater, String remark) {

        // 参数验证
        Tool tool = toolBO.getTool(code);

        // 是否可上架
        if (!EToolStatus.UP.getCode().equals(tool.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前道具不是可下架状态");
        }

        toolBO.refreshStatus(tool, updater, remark);
    }
}
