package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IHandicapDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Handicap;

@Repository("handicapDAOImpl")
public class HandicapDAOImpl extends AMybatisTemplate implements IHandicapDAO {

    @Override
    public int insert(Handicap data) {
        return super.insert(NAMESPACE.concat("insert_handicap"), data);
    }

    @Override
    public int delete(Handicap data) {
        return super.delete(NAMESPACE.concat("delete_handicap"), data);
    }

    @Override
    public Handicap select(Handicap condition) {
        return super.select(NAMESPACE.concat("select_handicap"), condition,
            Handicap.class);
    }

    @Override
    public long selectTotalCount(Handicap condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_handicap_count"),
            condition);
    }

    @Override
    public List<Handicap> selectList(Handicap condition) {
        return super.selectList(NAMESPACE.concat("select_handicap"), condition,
            Handicap.class);
    }

    @Override
    public List<Handicap> selectList(Handicap condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_handicap"), start,
            count, condition, Handicap.class);
    }

    @Override
    public int update(Handicap data) {
        return super.update(NAMESPACE.concat("update_handicap"), data);
    }

}
