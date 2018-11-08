package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPresellSpecsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PresellSpecs;

@Repository("presellSpecsDAOImpl")
public class PresellSpecsDAOImpl extends AMybatisTemplate
        implements IPresellSpecsDAO {

    @Override
    public int insert(PresellSpecs data) {
        return super.insert(NAMESPACE.concat("insert_presellSpecs"), data);
    }

    @Override
    public int delete(PresellSpecs data) {
        return super.delete(NAMESPACE.concat("delete_byProduct"), data);
    }

    @Override
    public int updatePackCount(PresellSpecs data) {
        return super.update(NAMESPACE.concat("update_packCount"), data);
    }

    @Override
    public int updatePrice(PresellSpecs data) {
        return super.update(NAMESPACE.concat("update_price"), data);
    }

    @Override
    public PresellSpecs select(PresellSpecs condition) {
        return super.select(NAMESPACE.concat("select_presellSpecs"), condition,
            PresellSpecs.class);
    }

    @Override
    public long selectTotalCount(PresellSpecs condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_presellSpecs_count"), condition);
    }

    @Override
    public List<PresellSpecs> selectList(PresellSpecs condition) {
        return super.selectList(NAMESPACE.concat("select_presellSpecs"),
            condition, PresellSpecs.class);
    }

    @Override
    public List<PresellSpecs> selectList(PresellSpecs condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_presellSpecs"), start,
            count, condition, PresellSpecs.class);
    }

}
