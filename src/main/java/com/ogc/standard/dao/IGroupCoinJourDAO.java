package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupCoinJour;

public interface IGroupCoinJourDAO extends IBaseDAO<GroupCoinJour> {
	String NAMESPACE = IGroupCoinJourDAO.class.getName().concat(".");
}