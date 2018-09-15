package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDivideDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Divide;

@Repository("divideDAOImpl")
public class DivideDAOImpl extends AMybatisTemplate implements IDivideDAO {

    @Override
    public int insert(Divide data) {
        return super.insert(NAMESPACE.concat("insert_divide"), data);
    }

    @Override
    public int delete(Divide data) {
        return super.delete(NAMESPACE.concat("delete_divide"), data);
    }

    @Override
    public Divide select(Divide condition) {
        return super.select(NAMESPACE.concat("select_divide"), condition,
            Divide.class);
    }

    @Override
    public long selectTotalCount(Divide condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_divide_count"),
            condition);
    }

    @Override
    public List<Divide> selectList(Divide condition) {
        return super.selectList(NAMESPACE.concat("select_divide"), condition,
            Divide.class);
    }

    @Override
    public List<Divide> selectList(Divide condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_divide"), start, count,
            condition, Divide.class);
    }

    @Override
    public int update(Divide data) {
        return super.update(NAMESPACE.concat("uodate_divide"), data);
    }

}
