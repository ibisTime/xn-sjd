package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDivideDetailDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.DivideDetail;

@Repository("divideDetailDAOImpl")
public class DivideDetailDAOImpl extends AMybatisTemplate
        implements IDivideDetailDAO {

    @Override
    public int insert(DivideDetail data) {
        return super.insert(NAMESPACE.concat("insert_divideDetail"), data);
    }

    @Override
    public int delete(DivideDetail data) {
        return super.delete(NAMESPACE.concat("delete_divideDetail"), data);
    }

    @Override
    public DivideDetail select(DivideDetail condition) {
        return super.select(NAMESPACE.concat("select_divideDetail"), condition,
            DivideDetail.class);
    }

    @Override
    public long selectTotalCount(DivideDetail condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_divideDetail_count"), condition);
    }

    @Override
    public List<DivideDetail> selectList(DivideDetail condition) {
        return super.selectList(NAMESPACE.concat("select_divideDetail"),
            condition, DivideDetail.class);
    }

    @Override
    public List<DivideDetail> selectList(DivideDetail condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_divideDetail"), start,
            count, condition, DivideDetail.class);
    }

    @Override
    public int update(DivideDetail data) {
        return super.update(NAMESPACE.concat("update_divideDetail"), data);
    }

}
