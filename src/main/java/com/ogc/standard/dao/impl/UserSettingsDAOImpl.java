package com.ogc.standard.dao.impl;

import com.ogc.standard.dao.IUserSettingsDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.UserSettings;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository("userSettingsDAOImpl")
public class UserSettingsDAOImpl extends AMybatisTemplate implements IUserSettingsDAO {


	@Override
	public int insert(UserSettings data) {
		return super.insert(NAMESPACE.concat("insert_UserSettings"), data);
	}


	@Override
	public int delete(UserSettings data) {
		return super.delete(NAMESPACE.concat("delete"), data);
	}


	@Override
	public UserSettings select(UserSettings condition) {
		return super.select(NAMESPACE.concat("select_UserSettings"), condition,UserSettings.class);
	}


	@Override
	public long selectTotalCount(UserSettings condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_UserSettings_count"),condition);
	}


	@Override
	public List<UserSettings> selectList(UserSettings condition) {
		return super.selectList(NAMESPACE.concat("select_UserSettings"), condition,UserSettings.class);
	}


	@Override
	public List<UserSettings> selectList(UserSettings condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_UserSettings"), start, count,condition, UserSettings.class);
	}


}