package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IMaintainerDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Maintainer;

@Repository("maintainerDAOImpl")
public class MaintainerDAOImpl extends AMybatisTemplate
        implements IMaintainerDAO {

    @Override
    public int insert(Maintainer data) {
        return super.insert(NAMESPACE.concat("insert_maintainer"), data);
    }

    @Override
    public int delete(Maintainer data) {
        return super.delete(NAMESPACE.concat("delete_maintainer"), data);
    }

    @Override
    public int updateMaintainer(Maintainer data) {
        return super.update(NAMESPACE.concat("update_maintainer"), data);
    }

    @Override
    public Maintainer select(Maintainer condition) {
        return super.select(NAMESPACE.concat("select_maintainer"), condition,
            Maintainer.class);
    }

    @Override
    public long selectTotalCount(Maintainer condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_maintainer_count"), condition);
    }

    @Override
    public List<Maintainer> selectList(Maintainer condition) {
        return super.selectList(NAMESPACE.concat("select_maintainer"),
            condition, Maintainer.class);
    }

    @Override
    public List<Maintainer> selectList(Maintainer condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_maintainer"), start,
            count, condition, Maintainer.class);
    }

}
