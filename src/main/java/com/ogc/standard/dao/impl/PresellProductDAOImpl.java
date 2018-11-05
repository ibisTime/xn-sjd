package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IPresellProductDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.PresellProduct;

@Repository("presellProductDAOImpl")
public class PresellProductDAOImpl extends AMybatisTemplate
        implements IPresellProductDAO {

    @Override
    public int insert(PresellProduct data) {
        return super.insert(NAMESPACE.concat("insert_presellProduct"), data);
    }

    @Override
    public int delete(PresellProduct data) {
        return super.delete(NAMESPACE.concat("delete_presellProduct"), data);
    }

    @Override
    public int updateEditPresellProduct(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_editPresellProduct"),
            data);
    }

    @Override
    public int updateSubmitPresellProduct(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_submitPresellProduct"),
            data);
    }

    @Override
    public int updateApprovePresellProduct(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_approvePresellProduct"),
            data);
    }

    @Override
    public int updatePutOnPresellProduct(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_putOnPresellProduct"),
            data);
    }

    @Override
    public int updatePutOffPresellProduct(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_putOffPresellProduct"),
            data);
    }

    @Override
    public int updateNowCount(PresellProduct data) {
        return super.update(NAMESPACE.concat("update_nowCount"), data);
    }

    @Override
    public PresellProduct select(PresellProduct condition) {
        return super.select(NAMESPACE.concat("select_presellProduct"),
            condition, PresellProduct.class);
    }

    @Override
    public long selectTotalCount(PresellProduct condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_presellProduct_count"), condition);
    }

    @Override
    public List<PresellProduct> selectList(PresellProduct condition) {
        return super.selectList(NAMESPACE.concat("select_presellProduct"),
            condition, PresellProduct.class);
    }

    @Override
    public List<PresellProduct> selectList(PresellProduct condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_presellProduct"),
            start, count, condition, PresellProduct.class);
    }

}
