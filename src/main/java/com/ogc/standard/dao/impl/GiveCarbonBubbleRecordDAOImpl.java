package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IGiveCarbonBubbleRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;

@Repository("giveCarbonBubbleRecordDAOImpl")
public class GiveCarbonBubbleRecordDAOImpl extends AMybatisTemplate
        implements IGiveCarbonBubbleRecordDAO {

    @Override
    public int insert(GiveCarbonBubbleRecord data) {
        return super.insert(NAMESPACE.concat("insert_giveCarbonBubbleRecord"),
            data);
    }

    @Override
    public int delete(GiveCarbonBubbleRecord data) {
        return 0;
    }

    @Override
    public GiveCarbonBubbleRecord select(GiveCarbonBubbleRecord condition) {
        return super.select(NAMESPACE.concat("select_giveCarbonBubbleRecord"),
            condition, GiveCarbonBubbleRecord.class);
    }

    @Override
    public long selectTotalCount(GiveCarbonBubbleRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_giveCarbonBubbleRecord_count"), condition);
    }

    @Override
    public List<GiveCarbonBubbleRecord> selectList(
            GiveCarbonBubbleRecord condition) {
        return super.selectList(
            NAMESPACE.concat("select_giveCarbonBubbleRecord"), condition,
            GiveCarbonBubbleRecord.class);
    }

    @Override
    public List<GiveCarbonBubbleRecord> selectList(
            GiveCarbonBubbleRecord condition, int start, int count) {
        return super.selectList(
            NAMESPACE.concat("select_giveCarbonBubbleRecord"), start, count,
            condition, GiveCarbonBubbleRecord.class);
    }

}
