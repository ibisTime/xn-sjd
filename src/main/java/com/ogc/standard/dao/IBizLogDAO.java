package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BizLog;

//dao层 
public interface IBizLogDAO extends IBaseDAO<BizLog> {
	String NAMESPACE = IBizLogDAO.class.getName().concat(".");
}