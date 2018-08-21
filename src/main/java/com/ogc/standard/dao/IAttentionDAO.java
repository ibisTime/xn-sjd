package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Attention;

public interface IAttentionDAO extends IBaseDAO<Attention> {
	String NAMESPACE = IAttentionDAO.class.getName().concat(".");
}