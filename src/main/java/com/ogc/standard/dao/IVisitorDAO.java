package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Visitor;

//dao层 
public interface IVisitorDAO extends IBaseDAO<Visitor> {
    String NAMESPACE = IVisitorDAO.class.getName().concat(".");

    int update(Visitor data);
}
