package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IBarrageDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Barrage;

@Repository("barrageDAOImpl")
public class BarrageDAOImpl extends AMybatisTemplate implements IBarrageDAO {

    @Override
    public int insert(Barrage data) {
        return super.insert(NAMESPACE.concat("insert_barrage"), data);
    }

    @Override
    public int delete(Barrage data) {
        return super.delete(NAMESPACE.concat("delete_barrage"), data);
    }

    @Override
    public int updateBarrage(Barrage barrage) {
        return super.update(NAMESPACE.concat("update_barrage"), barrage);
    }

    @Override
    public int updateStatus(Barrage barrage) {
        return super.update(NAMESPACE.concat("update_status"), barrage);
    }

    @Override
    public Barrage select(Barrage condition) {
        return super.select(NAMESPACE.concat("select_barrage"), condition,
            Barrage.class);
    }

    @Override
    public long selectTotalCount(Barrage condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_barrage_count"),
            condition);
    }

    @Override
    public List<Barrage> selectList(Barrage condition) {
        return super.selectList(NAMESPACE.concat("select_barrage"), condition,
            Barrage.class);
    }

    @Override
    public List<Barrage> selectList(Barrage condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_barrage"), start,
            count, condition, Barrage.class);
    }

}
