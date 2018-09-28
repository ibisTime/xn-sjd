package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveTreeRecord;

@Component
public interface IGiveTreeRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addGiveTreeRecord(GiveTreeRecord data);

    public int dropGiveTreeRecord(String code);

    public int editGiveTreeRecord(GiveTreeRecord data);

    public Paginable<GiveTreeRecord> queryGiveTreeRecordPage(int start,
            int limit, GiveTreeRecord condition);

    public List<GiveTreeRecord> queryGiveTreeRecordList(GiveTreeRecord condition);

    public GiveTreeRecord getGiveTreeRecord(String code);

}
