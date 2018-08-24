package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IExchangePairDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ExchangePair;

@Repository("exchangePairDAOImpl")
public class ExchangePairDAOImpl extends AMybatisTemplate
        implements IExchangePairDAO {

    @Override
    public int insert(ExchangePair data) {
        return super.insert(NAMESPACE.concat("insert_exchangePair"), data);
    }

    @Override
    public int delete(ExchangePair data) {
        return super.delete(NAMESPACE.concat("delete_exchangePair"), data);
    }

    @Override
    public ExchangePair select(ExchangePair condition) {
        return super.select(NAMESPACE.concat("select_exchangePair"), condition,
            ExchangePair.class);
    }

    @Override
    public long selectTotalCount(ExchangePair condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_exchangePair_count"), condition);
    }

    @Override
    public List<ExchangePair> selectList(ExchangePair condition) {
        return super.selectList(NAMESPACE.concat("select_exchangePair"),
            condition, ExchangePair.class);
    }

    @Override
    public List<ExchangePair> selectList(ExchangePair condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_exchangePair"), start,
            count, condition, ExchangePair.class);
    }

    @Override
    public List<ExchangePair> selectListShow(ExchangePair condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_exchangePair_show"),
            start, count, condition, ExchangePair.class);
    }

}
