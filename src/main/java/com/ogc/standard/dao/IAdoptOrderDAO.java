package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdoptOrder;

//dao层 
public interface IAdoptOrderDAO extends IBaseDAO<AdoptOrder> {
    String NAMESPACE = IAdoptOrderDAO.class.getName().concat(".");

    int update(AdoptOrder data);
}
