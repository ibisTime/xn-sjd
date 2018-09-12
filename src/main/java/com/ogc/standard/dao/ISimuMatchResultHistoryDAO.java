package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SimuMatchResultHistory;

public interface ISimuMatchResultHistoryDAO extends IBaseDAO<SimuMatchResultHistory> {
	String NAMESPACE = ISimuMatchResultHistoryDAO.class.getName().concat(".");
}