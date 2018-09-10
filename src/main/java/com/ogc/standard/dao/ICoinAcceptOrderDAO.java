package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CoinAcceptOrder;

public interface ICoinAcceptOrderDAO extends IBaseDAO<CoinAcceptOrder> {
	String NAMESPACE = ICoinAcceptOrderDAO.class.getName().concat(".");
}