package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IVisitorDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Visitor;

@Repository("visitorDAOImpl")
public class VisitorDAOImpl extends AMybatisTemplate implements IVisitorDAO {

    @Override
    public int insert(Visitor data) {
        return super.insert(NAMESPACE.concat("insert_visitor"), data);
    }

    @Override
    public int delete(Visitor data) {
        return super.delete(NAMESPACE.concat("delete_visitor"), data);
    }

    @Override
    public Visitor select(Visitor condition) {
        return super.select(NAMESPACE.concat("select_visitor"), condition,
            Visitor.class);
    }

    @Override
    public long selectTotalCount(Visitor condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_visitor_count"),
            condition);
    }

    @Override
    public List<Visitor> selectList(Visitor condition) {
        return super.selectList(NAMESPACE.concat("select_visitor"), condition,
            Visitor.class);
    }

    @Override
    public List<Visitor> selectList(Visitor condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_visitor"), start,
            count, condition, Visitor.class);
    }

    @Override
    public int update(Visitor data) {
        return super.update(NAMESPACE.concat("update_visitor"), data);
    }

}
