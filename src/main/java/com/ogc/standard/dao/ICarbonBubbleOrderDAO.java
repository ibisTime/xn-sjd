package com.ogc.standard.dao;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CarbonBubbleOrder;

//dao层 
public interface ICarbonBubbleOrderDAO extends IBaseDAO<CarbonBubbleOrder> {
    String NAMESPACE = ICarbonBubbleOrderDAO.class.getName().concat(".");

    // 收取
    void updateTakeCarbonBubble(CarbonBubbleOrder data);

    // 过期
    public int updateExpireCarbonBubble(CarbonBubbleOrder data);

    // 碳泡泡数量总额
    public BigDecimal selectTotalQuantity(CarbonBubbleOrder data);
}
