package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ApplyBindMaintain;

//dao层 
public interface IApplyBindMaintainDAO extends IBaseDAO<ApplyBindMaintain> {
    String NAMESPACE = IApplyBindMaintainDAO.class.getName().concat(".");

    int update(ApplyBindMaintain data);

    void approveApplyBindMaintain(ApplyBindMaintain data);
}
