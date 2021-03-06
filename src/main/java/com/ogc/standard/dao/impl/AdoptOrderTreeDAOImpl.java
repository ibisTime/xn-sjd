package com.ogc.standard.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAdoptOrderTreeDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AdoptOrderTree;

@Repository("adoptOrderTreeDAOImpl")
public class AdoptOrderTreeDAOImpl extends AMybatisTemplate
        implements IAdoptOrderTreeDAO {

    @Override
    public int insert(AdoptOrderTree data) {
        return super.insert(NAMESPACE.concat("insert_adoptOrderTree"), data);
    }

    @Override
    public int delete(AdoptOrderTree data) {
        return 0;
    }

    @Override
    public AdoptOrderTree select(AdoptOrderTree condition) {
        return super.select(NAMESPACE.concat("select_adoptOrderTree"),
            condition, AdoptOrderTree.class);
    }

    @Override
    public long selectTotalCount(AdoptOrderTree condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_adoptOrderTree_count"), condition);
    }

    @Override
    public long selectDistinctTotalCount(AdoptOrderTree condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_distinctAdoptOrderTree_count"), condition);
    }

    @Override
    public BigDecimal selectTotalAmount(AdoptOrderTree condition) {
        return super.select(NAMESPACE.concat("select_adoptOrderTree_amountSum"),
            condition, BigDecimal.class);
    }

    @Override
    public List<AdoptOrderTree> selectList(AdoptOrderTree condition) {
        return super.selectList(NAMESPACE.concat("select_adoptOrderTree"),
            condition, AdoptOrderTree.class);
    }

    @Override
    public List<AdoptOrderTree> selectProductAdoptedOrder(
            AdoptOrderTree condition) {
        return super.selectList(
            NAMESPACE.concat("select_productAdoptedOrderTree"), condition,
            AdoptOrderTree.class);
    }

    @Override
    public List<AdoptOrderTree> selectDistictByTreeNumber(
            AdoptOrderTree condition) {
        return super.selectList(NAMESPACE.concat("select_distinctByTreeNumber"),
            condition, AdoptOrderTree.class);
    }

    @Override
    public List<AdoptOrderTree> selectList(AdoptOrderTree condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_adoptOrderTree"),
            start, count, condition, AdoptOrderTree.class);
    }

    @Override
    public int updateStatus(AdoptOrderTree data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateInvalidAdoptByOrder(AdoptOrderTree data) {
        return super.update(NAMESPACE.concat("update_invalidByOrder"), data);
    }

    @Override
    public int updateQuantity(AdoptOrderTree data) {
        return super.update(NAMESPACE.concat("update_quantity"), data);
    }

}
