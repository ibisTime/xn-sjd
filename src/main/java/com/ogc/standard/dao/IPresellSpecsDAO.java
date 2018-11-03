package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellSpecs;

public interface IPresellSpecsDAO extends IBaseDAO<PresellSpecs> {
    String NAMESPACE = IPresellSpecsDAO.class.getName().concat(".");
}
