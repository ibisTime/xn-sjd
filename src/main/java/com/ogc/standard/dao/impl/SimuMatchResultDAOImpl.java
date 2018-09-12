package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISimuMatchResultDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SimuMatchResult;

@Repository("simuMatchResultDAOImpl")
public class SimuMatchResultDAOImpl extends AMybatisTemplate
        implements ISimuMatchResultDAO {

    @Override
    public int insert(SimuMatchResult data) {
        return super.insert(NAMESPACE.concat("insert_simuMatchResult"), data);
    }

    @Override
    public int delete(SimuMatchResult data) {
        return super.delete(NAMESPACE.concat("delete_simuMatchResult"), data);
    }

    @Override
    public SimuMatchResult select(SimuMatchResult condition) {
        return super.select(NAMESPACE.concat("select_simuMatchResult"),
            condition, SimuMatchResult.class);
    }

    @Override
    public long selectTotalCount(SimuMatchResult condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_simuMatchResult_count"), condition);
    }

    @Override
    public List<SimuMatchResult> selectList(SimuMatchResult condition) {
        return super.selectList(NAMESPACE.concat("select_simuMatchResult"),
            condition, SimuMatchResult.class);
    }

    @Override
    public List<SimuMatchResult> selectList(SimuMatchResult condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_simuMatchResult"),
            start, count, condition, SimuMatchResult.class);
    }

    @Override
    public int update(SimuMatchResult data) {
        return super.update(NAMESPACE.concat("update_simuMatchResult"), data);
    }

}
