package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IToolUseRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ToolUseRecord;

@Repository("toolUseRecordDAOImpl")
public class ToolUseRecordDAOImpl extends AMybatisTemplate
        implements IToolUseRecordDAO {

    @Override
    public int insert(ToolUseRecord data) {
        return super.insert(NAMESPACE.concat("insert_toolUseRecord"), data);
    }

    @Override
    public int delete(ToolUseRecord data) {
        return super.delete(NAMESPACE.concat("delete_toolUseRecord"), data);
    }

    @Override
    public ToolUseRecord select(ToolUseRecord condition) {
        return super.select(NAMESPACE.concat("select_toolUseRecord"), condition,
            ToolUseRecord.class);
    }

    @Override
    public long selectTotalCount(ToolUseRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_toolUseRecord_count"), condition);
    }

    @Override
    public List<ToolUseRecord> selectList(ToolUseRecord condition) {
        return super.selectList(NAMESPACE.concat("select_toolUseRecord"),
            condition, ToolUseRecord.class);
    }

    @Override
    public List<ToolUseRecord> selectList(ToolUseRecord condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_toolUseRecord"), start,
            count, condition, ToolUseRecord.class);
    }

    @Override
    public int updateStatus(ToolUseRecord condition) {
        return super.update(NAMESPACE.concat("update_status"), condition);
    }

}
