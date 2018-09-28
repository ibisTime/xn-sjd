package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGiveTreeRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GiveTreeRecord;

@Repository("giveTreeRecordDAOImpl")
public class GiveTreeRecordDAOImpl extends AMybatisTemplate implements
        IGiveTreeRecordDAO {

    @Override
    public int insert(GiveTreeRecord data) {
        return super.insert(NAMESPACE.concat("insert_giveTreeRecord"), data);
    }

    @Override
    public int delete(GiveTreeRecord data) {
        return super.delete(NAMESPACE.concat("delete_giveTreeRecord"), data);
    }

    @Override
    public GiveTreeRecord select(GiveTreeRecord condition) {
        return super.select(NAMESPACE.concat("select_giveTreeRecord"),
            condition, GiveTreeRecord.class);
    }

    @Override
    public long selectTotalCount(GiveTreeRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_giveTreeRecord_count"), condition);
    }

    @Override
    public List<GiveTreeRecord> selectList(GiveTreeRecord condition) {
        return super.selectList(NAMESPACE.concat("select_giveTreeRecord"),
            condition, GiveTreeRecord.class);
    }

    @Override
    public List<GiveTreeRecord> selectList(GiveTreeRecord condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_giveTreeRecord"),
            start, count, condition, GiveTreeRecord.class);
    }

    @Override
    public int update(GiveTreeRecord data) {
        return super.update(NAMESPACE.concat("update_giveTreeRecord"), data);
    }

}
