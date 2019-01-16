package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.StealCarbonBubbleRecord;

public interface IStealCarbonBubbleRecordDAO
        extends IBaseDAO<StealCarbonBubbleRecord> {
    String NAMESPACE = IStealCarbonBubbleRecordDAO.class.getName().concat(".");

    public Long selectSumQuantity(
            StealCarbonBubbleRecord stealCarbonBubbleRecord);

}
