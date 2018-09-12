package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SimuKLine;

public interface ISimuKLineDAO extends IBaseDAO<SimuKLine> {
	String NAMESPACE = ISimuKLineDAO.class.getName().concat(".");
}