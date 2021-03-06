package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.domain.ToolUseRecord;

public interface IToolUseRecordBO extends IPaginableBO<ToolUseRecord> {

    public String saveToolUseRecord(ToolOrder toolOrder,
            AdoptOrderTree adoptOrderTree, String userId);

    // 认养权的道具使用记录
    public List<ToolUseRecord> queryTreeToolRecordList(String adoptTreeCode,
            String toolType);

    public List<ToolUseRecord> queryTreeToolRecordList(String code);

    public List<ToolUseRecord> queryToolUseRecordList(ToolUseRecord condition);

    public ToolUseRecord getToolUseRecord(String code);

    public void doInvalid(ToolUseRecord toolUseRecord);

}
