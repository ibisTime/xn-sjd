package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDeriveGroupDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.DeriveGroup;

@Repository("deriveGroupDAOImpl")
public class DeriveGroupDAOImpl extends AMybatisTemplate
        implements IDeriveGroupDAO {

    @Override
    public int insert(DeriveGroup data) {
        return super.insert(NAMESPACE.concat("insert_deriveGroup"), data);
    }

    @Override
    public int delete(DeriveGroup data) {
        return super.delete(NAMESPACE.concat("delete_deriveGroup"), data);
    }

    @Override
    public int updateRevock(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_revockDeriveGroup"), data);
    }

    @Override
    public int updateClaimDirect(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_claimDirect"), data);
    }

    @Override
    public int updateRejectDirect(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_rejectDirect"), data);
    }

    @Override
    public int updateClaimQr(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_claimQr"), data);
    }

    @Override
    public int updateClaimPublic(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_claimPublic"), data);
    }

    @Override
    public int updateQuantity(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_quantity"), data);
    }

    @Override
    public int updateWave(DeriveGroup data) {
        return super.update(NAMESPACE.concat("update_wave"), data);
    }

    @Override
    public DeriveGroup select(DeriveGroup condition) {
        return super.select(NAMESPACE.concat("select_deriveGroup"), condition,
            DeriveGroup.class);
    }

    @Override
    public long selectTotalCount(DeriveGroup condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_deriveGroup_count"), condition);
    }

    @Override
    public List<DeriveGroup> selectList(DeriveGroup condition) {
        return super.selectList(NAMESPACE.concat("select_deriveGroup"),
            condition, DeriveGroup.class);
    }

    @Override
    public List<DeriveGroup> selectList(DeriveGroup condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_deriveGroup"), start,
            count, condition, DeriveGroup.class);
    }

    @Override
    public List<DeriveGroup> selectVarietyList(DeriveGroup data) {
        return super.selectList(NAMESPACE.concat("select_distinctVariety"),
            data, DeriveGroup.class);
    }

}
