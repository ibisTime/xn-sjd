package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ApplyBindMaintain;

//dao层 
public interface IApplyBindMaintainDAO extends IBaseDAO<ApplyBindMaintain> {
    String NAMESPACE = IApplyBindMaintainDAO.class.getName().concat(".");

    public int updateUnBindMaintain(ApplyBindMaintain data);

    public int updateReBindMaintain(ApplyBindMaintain data);

    void approveApplyBindMaintain(ApplyBindMaintain data);
}
