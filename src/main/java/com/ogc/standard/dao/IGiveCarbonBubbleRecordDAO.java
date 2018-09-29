package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;

/**
 * 赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 29, 2018 9:37:10 PM 
 * @history:
 */
public interface IGiveCarbonBubbleRecordDAO
        extends IBaseDAO<GiveCarbonBubbleRecord> {
    String NAMESPACE = IGiveCarbonBubbleRecordDAO.class.getName().concat(".");
}
