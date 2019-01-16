package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IStealCarbonBubbleRecordDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.StealCarbonBubbleRecord;

@Repository("stealCarbonBubbleRecordDAOImpl")
public class StealCarbonBubbleRecordDAOImpl extends AMybatisTemplate
        implements IStealCarbonBubbleRecordDAO {

    @Override
    public int insert(StealCarbonBubbleRecord data) {
        return super.insert(NAMESPACE.concat("insert_stealCarbonBubbleRecord"),
            data);
    }

    @Override
    public int delete(StealCarbonBubbleRecord data) {
        return super.delete(NAMESPACE.concat("delete_stealCarbonBubbleRecord"),
            data);
    }

    @Override
    public StealCarbonBubbleRecord select(StealCarbonBubbleRecord condition) {
        return super.select(NAMESPACE.concat("select_stealCarbonBubbleRecord"),
            condition, StealCarbonBubbleRecord.class);
    }

    @Override
    public long selectTotalCount(StealCarbonBubbleRecord condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_stealCarbonBubbleRecord_count"),
            condition);
    }

    @Override
    public List<StealCarbonBubbleRecord> selectList(
            StealCarbonBubbleRecord condition) {
        return super.selectList(
            NAMESPACE.concat("select_stealCarbonBubbleRecord"), condition,
            StealCarbonBubbleRecord.class);
    }

    @Override
    public List<StealCarbonBubbleRecord> selectList(
            StealCarbonBubbleRecord condition, int start, int count) {
        return super.selectList(
            NAMESPACE.concat("select_stealCarbonBubbleRecord"), start, count,
            condition, StealCarbonBubbleRecord.class);
    }

    @Override
    public Long selectSumQuantity(
            StealCarbonBubbleRecord stealCarbonBubbleRecord) {
        return super.select(NAMESPACE.concat("select_sumQuantity"),
            stealCarbonBubbleRecord, Long.class);
    }

}
