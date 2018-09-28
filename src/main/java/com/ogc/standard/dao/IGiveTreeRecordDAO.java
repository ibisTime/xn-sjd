package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GiveTreeRecord;

//dao层 
public interface IGiveTreeRecordDAO extends IBaseDAO<GiveTreeRecord> {
    String NAMESPACE = IGiveTreeRecordDAO.class.getName().concat(".");

    int update(GiveTreeRecord data);
}
