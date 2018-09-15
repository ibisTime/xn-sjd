package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Divide;

public interface IDivideDAO extends IBaseDAO<Divide> {
    String NAMESPACE = IDivideDAO.class.getName().concat(".");

    public int update(Divide data);
}
