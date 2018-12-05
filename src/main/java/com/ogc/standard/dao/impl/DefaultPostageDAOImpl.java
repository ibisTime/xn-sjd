package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDefaultPostageDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.DefaultPostage;

@Repository("defaultPostageDAOImpl")
public class DefaultPostageDAOImpl extends AMybatisTemplate
        implements IDefaultPostageDAO {

    @Override
    public int insert(DefaultPostage data) {
        return super.insert(NAMESPACE.concat("insert_defaultPostage"), data);
    }

    @Override
    public int delete(DefaultPostage data) {
        return super.delete(NAMESPACE.concat("delete_defaultPostage"), data);
    }

    @Override
    public int updatePrice(DefaultPostage defaultPostage) {
        return super.update(NAMESPACE.concat("update_price"), defaultPostage);
    }

    @Override
    public DefaultPostage select(DefaultPostage condition) {
        return super.select(NAMESPACE.concat("select_defaultPostage"),
            condition, DefaultPostage.class);
    }

    @Override
    public long selectTotalCount(DefaultPostage condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_defaultPostage_count"), condition);
    }

    @Override
    public List<DefaultPostage> selectList(DefaultPostage condition) {
        return super.selectList(NAMESPACE.concat("select_defaultPostage"),
            condition, DefaultPostage.class);
    }

    @Override
    public List<DefaultPostage> selectList(DefaultPostage condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_defaultPostage"),
            start, count, condition, DefaultPostage.class);
    }

}
