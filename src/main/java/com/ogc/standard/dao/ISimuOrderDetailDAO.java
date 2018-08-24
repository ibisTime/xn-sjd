package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SimuOrderDetail;

public interface ISimuOrderDetailDAO extends IBaseDAO<SimuOrderDetail> {
    String NAMESPACE = ISimuOrderDetailDAO.class.getName().concat(".");
}
