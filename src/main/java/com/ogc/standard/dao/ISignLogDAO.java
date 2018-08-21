package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SignLog;

public interface ISignLogDAO extends IBaseDAO<SignLog> {
    String NAMESPACE = ISignLogDAO.class.getName().concat(".");
}
