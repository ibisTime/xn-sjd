package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IApplyBindMaintainDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.ApplyBindMaintain;

@Repository("applyBindMaintainDAOImpl")
public class ApplyBindMaintainDAOImpl extends AMybatisTemplate implements
        IApplyBindMaintainDAO {

    @Override
    public int insert(ApplyBindMaintain data) {
        return super.insert(NAMESPACE.concat("insert_applyBindMaintain"), data);
    }

    @Override
    public int delete(ApplyBindMaintain data) {
        return super.delete(NAMESPACE.concat("delete_applyBindMaintain"), data);
    }

    @Override
    public ApplyBindMaintain select(ApplyBindMaintain condition) {
        return super.select(NAMESPACE.concat("select_applyBindMaintain"),
            condition, ApplyBindMaintain.class);
    }

    @Override
    public long selectTotalCount(ApplyBindMaintain condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_applyBindMaintain_count"), condition);
    }

    @Override
    public List<ApplyBindMaintain> selectList(ApplyBindMaintain condition) {
        return super.selectList(NAMESPACE.concat("select_applyBindMaintain"),
            condition, ApplyBindMaintain.class);
    }

    @Override
    public List<ApplyBindMaintain> selectList(ApplyBindMaintain condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_applyBindMaintain"),
            start, count, condition, ApplyBindMaintain.class);
    }

    @Override
    public int update(ApplyBindMaintain data) {
        // TODO Auto-generated method stub
        return 0;
    }

}
