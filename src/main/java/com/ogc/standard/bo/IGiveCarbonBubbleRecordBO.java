package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;

/**
 * 赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 29, 2018 9:36:52 PM 
 * @history:
 */
public interface IGiveCarbonBubbleRecordBO
        extends IPaginableBO<GiveCarbonBubbleRecord> {

    // 添加记录
    public String saveGiveCarbonBubbleRecord(String userId, String toUserId,
            BigDecimal quantity);

    public List<GiveCarbonBubbleRecord> queryGiveCarbonBubbleRecordList(
            GiveCarbonBubbleRecord condition);

    public GiveCarbonBubbleRecord getGiveCarbonBubbleRecord(String code);

}
