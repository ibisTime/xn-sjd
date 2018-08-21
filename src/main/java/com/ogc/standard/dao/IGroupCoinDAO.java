package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupCoin;

public interface IGroupCoinDAO extends IBaseDAO<GroupCoin> {
	String NAMESPACE = IGroupCoinDAO.class.getName().concat(".");
}