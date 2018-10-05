package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GiveTreeRecord;

public interface IGiveTreeRecordBO extends IPaginableBO<GiveTreeRecord> {

    public String saveGiveTreeRecord(String userId, String toUserId,
            String adoptTreeCode);

    public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition);

    public GiveTreeRecord getGiveTreeRecord(String code);

}
