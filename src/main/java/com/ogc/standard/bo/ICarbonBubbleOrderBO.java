package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CarbonBubbleOrder;

public interface ICarbonBubbleOrderBO extends IPaginableBO<CarbonBubbleOrder> {

    // 添加碳泡泡
    public String saveCarbonBubbleOrder(String adoptTreeCode,
            Date createDatetime, Date invalidDatetime, String adoptUserId,
            BigDecimal quantity);

    // 更新过期碳泡泡
    public void expireCarbonBubbleOrder(String code);

    // 收取碳泡泡
    public void takeCarbonBubble(String code, String collector);

    // 可收取的碳泡泡
    public BigDecimal takeableTppAmount(String adoptUserId);

    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition);

    public CarbonBubbleOrder getCarbonBubbleOrder(String code);

}
