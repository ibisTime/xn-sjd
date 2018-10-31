package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IToolBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IToolUseRecordDAO;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EToolUseRecordStatus;
import com.ogc.standard.exception.BizException;

@Component
public class ToolUseRecordBOImpl extends PaginableBOImpl<ToolUseRecord>
        implements IToolUseRecordBO {

    @Autowired
    private IToolUseRecordDAO toolUseRecordDAO;

    @Autowired
    private IToolBO toolBO;

    @Override
    public String saveToolUseRecord(ToolOrder toolOrder,
            AdoptOrderTree adoptOrderTree, String userId) {

        ToolUseRecord toolUseRecord = new ToolUseRecord();
        Tool tool = toolBO.getTool(toolOrder.getToolCode());

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TOOL_USE_RECORD.getCode());
        toolUseRecord.setCode(code);
        toolUseRecord.setToolCode(toolOrder.getToolCode());
        toolUseRecord.setToolOrderCode(toolOrder.getCode());
        toolUseRecord.setAdoptTreeCode(adoptOrderTree.getCode());
        toolUseRecord.setToolType(tool.getType());

        toolUseRecord.setStatus(EToolUseRecordStatus.ACTIVE.getCode());
        toolUseRecord.setUserId(userId);
        toolUseRecord.setCreateDatetime(new Date());
        toolUseRecord.setInvalidDatetime(DateUtil.getRelativeDateOfHour(
            new Date(), toolOrder.getValidityTerm().doubleValue()));

        toolUseRecordDAO.insert(toolUseRecord);
        return code;
    }

    @Override
    public List<ToolUseRecord> queryTreeToolRecordList(String adoptTreeCode,
            String toolType) {
        ToolUseRecord condition = new ToolUseRecord();
        condition.setAdoptTreeCode(adoptTreeCode);
        condition.setToolType(toolType);
        condition.setStatus(EToolUseRecordStatus.ACTIVE.getCode());
        return toolUseRecordDAO.selectList(condition);
    }

    @Override
    public List<ToolUseRecord> queryTreeToolRecordList(String code) {
        ToolUseRecord condition = new ToolUseRecord();
        condition.setCode(code);
        condition.setStatus(EToolUseRecordStatus.ACTIVE.getCode());
        return toolUseRecordDAO.selectList(condition);
    }

    @Override
    public List<ToolUseRecord> queryToolUseRecordList(ToolUseRecord condition) {
        return toolUseRecordDAO.selectList(condition);
    }

    @Override
    public ToolUseRecord getToolUseRecord(String code) {
        ToolUseRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            ToolUseRecord condition = new ToolUseRecord();
            condition.setCode(code);
            data = toolUseRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "道具使用记录不存在");
            }
        }
        return data;
    }

    @Override
    public void doInvalid(ToolUseRecord toolUseRecord) {
        toolUseRecord.setStatus(EToolUseRecordStatus.INVALID.getCode());
        toolUseRecordDAO.updateStatus(toolUseRecord);

    }

}
