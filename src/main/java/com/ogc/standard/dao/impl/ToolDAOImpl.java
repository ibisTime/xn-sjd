package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IToolDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Tool;

@Repository("toolDAOImpl")
public class ToolDAOImpl extends AMybatisTemplate implements IToolDAO {

    @Override
    public int insert(Tool data) {
        return 0;
    }

    @Override
    public int delete(Tool data) {
        return 0;
    }

    @Override
    public Tool select(Tool condition) {
        return super.select(NAMESPACE.concat("select_tool"), condition,
            Tool.class);
    }

    @Override
    public long selectTotalCount(Tool condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_tool_count"),
            condition);
    }

    @Override
    public List<Tool> selectList(Tool condition) {
        return super.selectList(NAMESPACE.concat("select_tool"), condition,
            Tool.class);
    }

    @Override
    public List<Tool> selectList(Tool condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_tool"), start, count,
            condition, Tool.class);
    }

    @Override
    public int update(Tool data) {
        return super.update(NAMESPACE.concat("update_tool"), data);
    }

    @Override
    public int updateUp(Tool data) {
        return super.update(NAMESPACE.concat("update_up"), data);
    }

    @Override
    public int updateDown(Tool data) {
        return super.update(NAMESPACE.concat("update_down"), data);
    }

}
