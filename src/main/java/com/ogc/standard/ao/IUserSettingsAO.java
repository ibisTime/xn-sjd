package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserSettings;

import java.util.List;



public interface IUserSettingsAO {

	static final String DEFAULT_ORDER_COLUMN = "id";

	public void addUserSettings(String userId, String type);

	public void dropUserSettings(String userId, String type);

	public Paginable<UserSettings> queryUserSettingsPage(int start, int limit, UserSettings condition);

	public List<UserSettings> queryUserSettingsList(UserSettings condition);


}