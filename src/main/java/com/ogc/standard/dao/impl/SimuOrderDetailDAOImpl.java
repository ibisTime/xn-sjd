package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISimuOrderDetailDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.SimuOrderDetail;

@Repository("simuOrderDetailDAOImpl")
public class SimuOrderDetailDAOImpl extends AMybatisTemplate
        implements ISimuOrderDetailDAO {

    @Override
    public int insert(SimuOrderDetail data) {
        return super.insert(NAMESPACE.concat("insert_simuOrderDetail"), data);
    }

    @Override
    public int delete(SimuOrderDetail data) {
        return 0;
    }

    @Override
    public SimuOrderDetail select(SimuOrderDetail condition) {
        return super.select(NAMESPACE.concat("select_simuOrderDetail"),
            condition, SimuOrderDetail.class);
    }

    @Override
    public long selectTotalCount(SimuOrderDetail condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_simuOrderDetail_count"), condition);
    }

    @Override
    public List<SimuOrderDetail> selectList(SimuOrderDetail condition) {
        return super.selectList(NAMESPACE.concat("select_simuOrderDetail"),
            condition, SimuOrderDetail.class);
    }

    @Override
    public List<SimuOrderDetail> selectList(SimuOrderDetail condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_simuOrderDetail"),
            start, count, condition, SimuOrderDetail.class);
    }

}
