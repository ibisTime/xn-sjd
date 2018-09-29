package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiveCarbonBubbleRecordAO;
import com.ogc.standard.bo.IGiveCarbonBubbleRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;

@Service
public class GiveCarbonBubbleRecordAOImpl implements IGiveCarbonBubbleRecordAO {

    @Autowired
    private IGiveCarbonBubbleRecordBO giveCarbonBubbleRecordBO;

    @Override
    public String addGiveCarbonBubbleRecord(String userId, String toUserId,
            Integer quantity) {
        return giveCarbonBubbleRecordBO.saveGiveCarbonBubbleRecord(userId,
            toUserId, quantity);
    }

    @Override
    public Paginable<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordPage(
            int start, int limit, GiveCarbonBubbleRecord condition) {
        return giveCarbonBubbleRecordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordList(
            GiveCarbonBubbleRecord condition) {
        return giveCarbonBubbleRecordBO
            .queryGiveCarbonBubbleRecordList(condition);
    }

    @Override
    public GiveCarbonBubbleRecord getGiveCarbonBubbleRecord(String code) {
        return giveCarbonBubbleRecordBO.getGiveCarbonBubbleRecord(code);
    }
}
