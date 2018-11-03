package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IOriginalGroupDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.OriginalGroup;

@Repository("originalGroupDAOImpl")
public class OriginalGroupDAOImpl extends AMybatisTemplate
        implements IOriginalGroupDAO {

    @Override
    public int insert(OriginalGroup data) {
        return super.insert(NAMESPACE.concat("insert_originalGroup"), data);
    }

    @Override
    public int delete(OriginalGroup data) {
        return super.delete(NAMESPACE.concat("delete_originalGroup"), data);
    }

    @Override
    public int updateAdoptOrignalGroup(OriginalGroup data) {
        return super.update(NAMESPACE.concat("update_adoptOriginalGroup"),
            data);
    }

    @Override
    public int updateQuantity(OriginalGroup data) {
        return super.update(NAMESPACE.concat("update_quantity"), data);
    }

    @Override
    public int updateDeliverOrignalGroup(OriginalGroup data) {
        return super.update(NAMESPACE.concat("update_deliver"), data);
    }

    @Override
    public int updateReceiveOrignalGroup(OriginalGroup data) {
        return super.update(NAMESPACE.concat("update_receive"), data);
    }

    @Override
    public OriginalGroup select(OriginalGroup condition) {
        return super.select(NAMESPACE.concat("select_originalGroup"), condition,
            OriginalGroup.class);
    }

    @Override
    public long selectTotalCount(OriginalGroup condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_originalGroup_count"), condition);
    }

    @Override
    public List<OriginalGroup> selectList(OriginalGroup condition) {
        return super.selectList(NAMESPACE.concat("select_originalGroup"),
            condition, OriginalGroup.class);
    }

    @Override
    public List<OriginalGroup> selectList(OriginalGroup condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_originalGroup"), start,
            count, condition, OriginalGroup.class);
    }

}
