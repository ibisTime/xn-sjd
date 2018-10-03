package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IToolOrderDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ToolOrder;

@Repository("toolOrderDAOImpl")
public class ToolOrderDAOImpl extends AMybatisTemplate
        implements IToolOrderDAO {

    @Override
    public int insert(ToolOrder data) {
        return super.insert(NAMESPACE.concat("insert_toolOrder"), data);
    }

    @Override
    public int delete(ToolOrder data) {
        return super.delete(NAMESPACE.concat("delete_toolOrder"), data);
    }

    @Override
    public ToolOrder select(ToolOrder condition) {
        return super.select(NAMESPACE.concat("select_toolOrder"), condition,
            ToolOrder.class);
    }

    @Override
    public long selectTotalCount(ToolOrder condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_toolOrder_count"), condition);
    }

    @Override
    public List<ToolOrder> selectList(ToolOrder condition) {
        return super.selectList(NAMESPACE.concat("select_toolOrder"), condition,
            ToolOrder.class);
    }

    @Override
    public List<ToolOrder> selectList(ToolOrder condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_toolOrder"), start,
            count, condition, ToolOrder.class);
    }

    @Override
    public int updateStatus(ToolOrder condition) {
        return super.update(NAMESPACE.concat("update_status"), condition);
    }

}
