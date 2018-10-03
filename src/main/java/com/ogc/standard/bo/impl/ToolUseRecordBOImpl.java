package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IToolUseRecordDAO;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EToolUseRecordStatus;
import com.ogc.standard.exception.BizException;

@Component
public class ToolUseRecordBOImpl extends PaginableBOImpl<ToolUseRecord>
        implements IToolUseRecordBO {

    @Autowired
    private IToolUseRecordDAO toolUseRecordDAO;

    @Override
    public String saveToolUseRecord(ToolOrder toolOrder,
            AdoptOrderTree adoptOrderTree, User user) {

        ToolUseRecord toolUseRecord = new ToolUseRecord();

        String code = OrderNoGenerater
            .generate(EGeneratePrefix.TOOL_USE_RECORD.getCode());
        toolUseRecord.setCode(code);
        toolUseRecord.setToolOrderCode(toolOrder.getCode());
        toolUseRecord.setAdoptTreeCode(adoptOrderTree.getCode());
        toolUseRecord.setStatus(EToolUseRecordStatus.ACTIVE.getCode());
        toolUseRecord.setUserId(user.getUserId());

        Date date = new Date();
        toolUseRecord.setCreateDatetime(date);
        toolUseRecord.setInvalidDatetime(
            DateUtil.getRelativeDateOfHour(date, toolOrder.getValidityTerm()));

        toolUseRecordDAO.insert(toolUseRecord);
        return code;
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
