package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CarbonBubbleOrder;

public interface ICarbonBubbleOrderBO extends IPaginableBO<CarbonBubbleOrder> {

    public String saveCarbonBubbleOrder(CarbonBubbleOrder data);

    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition);

    public CarbonBubbleOrder getCarbonBubbleOrder(String code);

    // 收取碳泡泡
    public void takeCarbonBubble(String code, String collector);

}
