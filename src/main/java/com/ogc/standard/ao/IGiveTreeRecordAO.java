package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveTreeRecord;

public interface IGiveTreeRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<GiveTreeRecord> queryGiveTreeRecordPage(int start,
            int limit, GiveTreeRecord condition);

    public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition);

    public GiveTreeRecord getGiveTreeRecord(String code);

}
