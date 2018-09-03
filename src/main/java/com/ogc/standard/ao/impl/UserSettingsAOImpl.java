package com.ogc.standard.ao.impl;

import com.ogc.standard.ao.IUserSettingsAO;
import com.ogc.standard.bo.IUserSettingsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.UserSettings;
import com.ogc.standard.enums.EUserSettingsType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserSettingsAOImpl implements IUserSettingsAO {

    @Autowired
    private IUserSettingsBO userSettingsBO;

    private static String yesValue = "1";

    @Override
    public void addUserSettings(String userId, String type) {

        if (!type.equals(EUserSettingsType.AUTO_HAOPING.getCode())
                &&
            !type.equals(EUserSettingsType.AUTO_TRUST.getCode())
                ) {
            //
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),"不支持的设置类型");
            //
        }

        //检查用户是否已经进行过该设置
        long count = this.userSettingsBO.checkUserSetting(userId, type);
        if (count > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "您已经添加过该设置");
        }
        //
        UserSettings userSettings = new UserSettings();
        userSettings.setUserId(userId);
        userSettings.setType(type);
        userSettings.setValue(yesValue);
        this.userSettingsBO.saveUserSettings(userSettings);
        //

    }

    @Override
    public void dropUserSettings(String userId, String type) {

        int count = this.userSettingsBO.removeUserSettings(userId, type);
        if (count != 1) {
            throw new BizException("xn000", "取消设置失败");
        }

    }

    @Override
    public Paginable<UserSettings> queryUserSettingsPage(int start, int limit,
                                                         UserSettings condition) {
        return userSettingsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<UserSettings> queryUserSettingsList(UserSettings condition) {
        return userSettingsBO.queryUserSettingsList(condition);
    }


}