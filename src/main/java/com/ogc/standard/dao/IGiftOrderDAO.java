package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GiftOrder;

//dao层 
public interface IGiftOrderDAO extends IBaseDAO<GiftOrder> {
    String NAMESPACE = IGiftOrderDAO.class.getName().concat(".");

    int update(GiftOrder data);
}
