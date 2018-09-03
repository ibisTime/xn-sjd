package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.UserSettings;

public interface IUserSettingsDAO extends IBaseDAO<UserSettings> {
	String NAMESPACE = IUserSettingsDAO.class.getName().concat(".");
}