package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;

/**
 * 赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 29, 2018 9:41:46 PM 
 * @history:
 */
public interface IGiveCarbonBubbleRecordAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 赠送碳泡泡
    public String addGiveCarbonBubbleRecord(String userId, String toUserId,
            Integer quantity);

    public Paginable<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordPage(
            int start, int limit, GiveCarbonBubbleRecord condition);

    public List<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordList(
            GiveCarbonBubbleRecord condition);

    public GiveCarbonBubbleRecord getGiveCarbonBubbleRecord(String code);

}
