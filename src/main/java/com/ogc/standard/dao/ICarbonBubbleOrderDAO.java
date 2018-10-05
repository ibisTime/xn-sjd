package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CarbonBubbleOrder;

//dao层 
public interface ICarbonBubbleOrderDAO extends IBaseDAO<CarbonBubbleOrder> {
    String NAMESPACE = ICarbonBubbleOrderDAO.class.getName().concat(".");

    void updateTakeCarbonBubble(CarbonBubbleOrder data);
}
