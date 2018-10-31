package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CarbonBubbleOrder;
import com.ogc.standard.dto.res.XN629350Res;

public interface ICarbonBubbleOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 收取碳泡泡
    public XN629350Res takeCarbonBubble(String code, String userId);

    // 收取认养权下的碳泡泡
    public XN629350Res takeCarbonBubbleByAdopt(String adoptTreeCode,
            String collector);

    public Paginable<CarbonBubbleOrder> queryCarbonBubbleOrderPage(int start,
            int limit, CarbonBubbleOrder condition);

    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition);

    public CarbonBubbleOrder getCarbonBubbleOrder(String code);

}
