package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.ISettleDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Settle;

@Repository("settleDAOImpl")
public class SettleDAOImpl extends AMybatisTemplate implements ISettleDAO {

    @Override
    public int insert(Settle data) {
        return super.insert(NAMESPACE.concat("insert_settle"), data);
    }

    @Override
    public int delete(Settle data) {
        return 0;
    }

    @Override
    public int updateStatusByRef(Settle data) {
        return super.update(NAMESPACE.concat("update_settleStatusByRef"), data);
    }

    @Override
    public Settle select(Settle condition) {
        return super.select(NAMESPACE.concat("select_settle"), condition,
            Settle.class);
    }

    @Override
    public long selectTotalCount(Settle condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_settle_count"),
            condition);
    }

    @Override
    public List<Settle> selectList(Settle condition) {
        return super.selectList(NAMESPACE.concat("select_settle"), condition,
            Settle.class);
    }

    @Override
    public List<Settle> selectList(Settle condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_settle"), start, count,
            condition, Settle.class);
    }

}
