package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import com.ogc.standard.exception.EBizErrorCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IUserSettingsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IUserSettingsDAO;
import com.ogc.standard.domain.UserSettings;
import com.ogc.standard.exception.BizException;


@Component
public class UserSettingsBOImpl extends PaginableBOImpl<UserSettings> implements IUserSettingsBO {

    @Autowired
    private IUserSettingsDAO UserSettingsDAO;

    @Override
    public boolean isUserSettingsExist(String code) {
        UserSettings condition = new UserSettings();
        if (UserSettingsDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveUserSettings(UserSettings data) {

        data.setUpdateDatetime(new Date());
        data.setCreateDatetime(new Date());
        int count = UserSettingsDAO.insert(data);
        if (count != 1) {

            throw new BizException("xn000", "添加设置失败");

        }

    }

    @Override
    public int removeUserSettings(String userId, String type) {
        int count = 0;
        UserSettings deleteCondition = new UserSettings();
        deleteCondition.setUserId(userId);
        deleteCondition.setType(type);
        count = this.UserSettingsDAO.delete(deleteCondition);
        return count;
    }

    @Override
    public int refreshUserSettings(UserSettings data) {
        int count = 0;

        return count;
    }

    @Override
    public List<UserSettings> queryUserSettingsList(UserSettings condition) {
        return UserSettingsDAO.selectList(condition);
    }

    @Override
    public long checkUserSetting(String userId, String type) {

        UserSettings userSettings = new UserSettings();
        userSettings.setUserId(userId);
        userSettings.setType(type);
        long count = this.UserSettingsDAO.selectTotalCount(userSettings);

        return count;

    }


    //	@Override
//	public UserSettings getUserSettings(in code) {
//		UserSettings data = null;
//		if (StringUtils.isNotBlank(code)) {
//			UserSettings condition = new UserSettings();
//			condition.setCode(code);
//			data = UserSettingsDAO.select(condition);
//			if (data == null) {
//				throw new BizException("xn0000", "记录不存在");
//			}
//		}
//		return data;
//	}
}