package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ToolOrder;

public interface IToolOrderDAO extends IBaseDAO<ToolOrder> {
	String NAMESPACE = IToolOrderDAO.class.getName().concat(".");
}