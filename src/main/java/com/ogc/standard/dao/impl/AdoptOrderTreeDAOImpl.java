package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IAdoptOrderTreeDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.AdoptOrderTree;

@Repository("adoptOrderTreeDAOImpl")
public class AdoptOrderTreeDAOImpl extends AMybatisTemplate implements
        IAdoptOrderTreeDAO {

    @Override
    public int insert(AdoptOrderTree data) {
        return super.insert(NAMESPACE.concat("insert_adoptOrderTree"), data);
    }

    @Override
    public int delete(AdoptOrderTree data) {
        return super.delete(NAMESPACE.concat("delete_adoptOrderTree"), data);
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
    public List<AdoptOrderTree> selectList(AdoptOrderTree condition) {
        return super.selectList(NAMESPACE.concat("select_adoptOrderTree"),
            condition, AdoptOrderTree.class);
    }

    @Override
    public List<AdoptOrderTree> selectList(AdoptOrderTree condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_adoptOrderTree"),
            start, count, condition, AdoptOrderTree.class);
    }

    @Override
    public int update(AdoptOrderTree data) {
        return super.update(NAMESPACE.concat("update_adoptOrderTree"), data);
    }

    @Override
    public void giveTree(AdoptOrderTree data) {
        super.update(NAMESPACE.concat("update_giveTree"), data);
    }

}
