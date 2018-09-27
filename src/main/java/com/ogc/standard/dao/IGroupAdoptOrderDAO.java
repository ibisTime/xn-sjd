package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupAdoptOrder;

//dao层 
public interface IGroupAdoptOrderDAO extends IBaseDAO<GroupAdoptOrder> {
    String NAMESPACE = IGroupAdoptOrderDAO.class.getName().concat(".");

    int update(GroupAdoptOrder data);
}
