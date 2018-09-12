package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SimuOrderHistory;

public interface ISimuOrderHistoryDAO extends IBaseDAO<SimuOrderHistory> {
	String NAMESPACE = ISimuOrderHistoryDAO.class.getName().concat(".");
}