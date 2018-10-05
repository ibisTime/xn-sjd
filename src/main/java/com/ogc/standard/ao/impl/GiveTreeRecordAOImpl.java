package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveTreeRecord;

@Service
public class GiveTreeRecordAOImpl implements IGiveTreeRecordAO {

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Override
    public Paginable<GiveTreeRecord> queryGiveTreeRecordPage(int start,
            int limit, GiveTreeRecord condition) {
        return giveTreeRecordBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition) {
        return giveTreeRecordBO.queryGiveTreeRecordList(condition);
    }

    @Override
    public GiveTreeRecord getGiveTreeRecord(String code) {
        return giveTreeRecordBO.getGiveTreeRecord(code);
    }
}
