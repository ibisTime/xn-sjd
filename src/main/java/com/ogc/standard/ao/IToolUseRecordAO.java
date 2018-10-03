package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ToolUseRecord;

public interface IToolUseRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<ToolUseRecord> queryToolUseRecordPage(int start, int limit,
            ToolUseRecord condition);

    public List<ToolUseRecord> queryToolUseRecordList(ToolUseRecord condition);

    public ToolUseRecord getToolUseRecord(String code);

}
