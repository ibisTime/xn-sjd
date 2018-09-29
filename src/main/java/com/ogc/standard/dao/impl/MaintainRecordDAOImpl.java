package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IMaintainRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.MaintainRecord;

@Repository("maintainRecordDAOImpl")
public class MaintainRecordDAOImpl extends AMybatisTemplate
        implements IMaintainRecordDAO {

    @Override
    public int insert(MaintainRecord data) {
        return super.insert(NAMESPACE.concat("insert_maintainRecord"), data);
    }

    @Override
    public int delete(MaintainRecord data) {
        return super.delete(NAMESPACE.concat("delete_maintainRecord"), data);
    }

    @Override
    public int updateMaintainRecord(MaintainRecord data) {
        return super.update(NAMESPACE.concat("update_maintainRecord"), data);
    }

    @Override
    public MaintainRecord select(MaintainRecord condition) {
        return super.select(NAMESPACE.concat("select_maintainRecord"),
            condition, MaintainRecord.class);
    }

    @Override
    public long selectTotalCount(MaintainRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_maintainRecord_count"), condition);
    }

    @Override
    public List<MaintainRecord> selectList(MaintainRecord condition) {
        return super.selectList(NAMESPACE.concat("select_maintainRecord"),
            condition, MaintainRecord.class);
    }

    @Override
    public List<MaintainRecord> selectList(MaintainRecord condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_maintainRecord"),
            start, count, condition, MaintainRecord.class);
    }

}
