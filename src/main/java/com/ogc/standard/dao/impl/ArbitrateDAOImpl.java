package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IArbitrateDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Arbitrate;

@Repository("arbitrateDAOImpl")
public class ArbitrateDAOImpl extends AMybatisTemplate implements IArbitrateDAO {

    @Override
    public int insert(Arbitrate data) {
        return super.insert(NAMESPACE.concat("insert_arbitrate"), data);
    }

    @Override
    public int delete(Arbitrate data) {
        return super.delete(NAMESPACE.concat("delete_arbitrate"), data);
    }

    @Override
    public Arbitrate select(Arbitrate condition) {
        return super.select(NAMESPACE.concat("select_arbitrate"), condition,
            Arbitrate.class);
    }

    @Override
    public long selectTotalCount(Arbitrate condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_arbitrate_count"), condition);
    }

    @Override
    public List<Arbitrate> selectList(Arbitrate condition) {
        return super.selectList(NAMESPACE.concat("select_arbitrate"),
            condition, Arbitrate.class);
    }

    @Override
    public List<Arbitrate> selectList(Arbitrate condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_arbitrate"), start,
            count, condition, Arbitrate.class);
    }

    @Override
    public int updateHandle(Arbitrate data) {
        return super.update(NAMESPACE.concat("update_handle"), data);
    }

    @Override
    public int updateCancel(Arbitrate data) {
        return super.update(NAMESPACE.concat("update_cancel"), data);
    }

}
