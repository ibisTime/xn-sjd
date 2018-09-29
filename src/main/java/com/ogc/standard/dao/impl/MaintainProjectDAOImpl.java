package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IMaintainProjectDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.MaintainProject;

@Repository("maintainProjectDAOImpl")
public class MaintainProjectDAOImpl extends AMybatisTemplate
        implements IMaintainProjectDAO {

    @Override
    public int insert(MaintainProject data) {
        return super.insert(NAMESPACE.concat("insert_maintainProject"), data);
    }

    @Override
    public int updateMaintainProject(MaintainProject data) {
        return super.update(NAMESPACE.concat("update_maintainProject"), data);
    }

    @Override
    public int delete(MaintainProject data) {
        return super.delete(NAMESPACE.concat("delete_maintainProject"), data);
    }

    @Override
    public MaintainProject select(MaintainProject condition) {
        return super.select(NAMESPACE.concat("select_maintainProject"),
            condition, MaintainProject.class);
    }

    @Override
    public long selectTotalCount(MaintainProject condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_maintainProject_count"), condition);
    }

    @Override
    public List<MaintainProject> selectList(MaintainProject condition) {
        return super.selectList(NAMESPACE.concat("select_maintainProject"),
            condition, MaintainProject.class);
    }

    @Override
    public List<MaintainProject> selectList(MaintainProject condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_maintainProject"),
            start, count, condition, MaintainProject.class);
    }

}
