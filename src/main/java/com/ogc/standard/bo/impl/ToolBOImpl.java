package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.IToolDAO;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629502Req;
import com.ogc.standard.enums.EToolStatus;
import com.ogc.standard.exception.BizException;

@Component
public class ToolBOImpl extends PaginableBOImpl<Tool> implements IToolBO {

    @Autowired
    private IToolDAO toolDAO;

    @Override
    public int removeTool(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Tool data = new Tool();
            data.setCode(code);
            count = toolDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshTool(Tool data, XN629502Req req) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            data.setName(req.getName());
            data.setType(req.getType());
            data.setPic(req.getPic());
            data.setPrice(req.getPrice());
            data.setDescription(req.getDescription());

            data.setValidityTerm(req.getValidityTerm());
            data.setStatus(req.getStatus());
            data.setOrderNo(req.getOrderNo());
            data.setUpdater(req.getUpdater());
            data.setUpdateDatetime(new Date());

            data.setRemark(req.getRemark());
        }
        return count;
    }

    @Override
    public List<Tool> queryToolList(Tool condition) {
        return toolDAO.selectList(condition);
    }

    @Override
    public Tool getTool(String code) {
        Tool data = null;
        if (StringUtils.isNotBlank(code)) {
            Tool condition = new Tool();
            condition.setCode(code);
            data = toolDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "道具不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshStatus(Tool tool, String updater, String remark) {

        if (!EToolStatus.DOWN.getCode().equals(tool.getStatus())) {
            tool.setStatus(EToolStatus.UP.getCode());
        } else {
            tool.setStatus(EToolStatus.DOWN.getCode());
        }

        tool.setUpdater(updater);
        tool.setRemark(remark);
        tool.setUpdateDatetime(new Date());
        toolDAO.updateStatus(tool);

    }
}
