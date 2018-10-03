package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ToolUseRecord;

public interface IToolUseRecordDAO extends IBaseDAO<ToolUseRecord> {
    String NAMESPACE = IToolUseRecordDAO.class.getName().concat(".");

    public int updateStatus(ToolUseRecord condition);
}
