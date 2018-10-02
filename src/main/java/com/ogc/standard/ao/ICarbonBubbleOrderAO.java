package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CarbonBubbleOrder;

@Component
public interface ICarbonBubbleOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCarbonBubbleOrder(CarbonBubbleOrder data);

    public int dropCarbonBubbleOrder(String code);

    public int editCarbonBubbleOrder(CarbonBubbleOrder data);

    public Paginable<CarbonBubbleOrder> queryCarbonBubbleOrderPage(int start,
            int limit, CarbonBubbleOrder condition);

    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition);

    public CarbonBubbleOrder getCarbonBubbleOrder(String code);

    // 收取碳泡泡
    public void takeCarbonBubble(String code, String collector);

}
