package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.StealCarbonBubbleRecord;

public interface IStealCarbonBubbleRecordBO
        extends IPaginableBO<StealCarbonBubbleRecord> {

    public String saveStealCarbonBubbleRecord(String stealUserId,
            String stealedUserId, String adoptTreeCode,
            String carbonBubbleOrderCode, BigDecimal quantity);

    // 今天是否偷取
    public Boolean isTodayStealed(String stealUserId, String stealedUserId,
            String adoptTreeCode);

    // 获取偷取数量
    public Long getTodayStealQuantity(String stealUserId, String stealedUserId);

    public List<StealCarbonBubbleRecord> queryStealCarbonBubbleRecordList(
            StealCarbonBubbleRecord condition);

    public StealCarbonBubbleRecord getStealCarbonBubbleRecord(String code);

}
