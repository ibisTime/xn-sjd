package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 审核通知人类型
 * @author: silver 
 * @since: Jan 17, 2019 3:42:50 PM 
 * @history:
 */
public enum ENotifyUserType {
    OWNER_APPROVE("1", "产权方审核");

    public static Map<String, ENotifyUserType> getAccountTypeResultMap() {
        Map<String, ENotifyUserType> map = new HashMap<String, ENotifyUserType>();
        for (ENotifyUserType type : ENotifyUserType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ENotifyUserType getAccountType(String code) {
        Map<String, ENotifyUserType> map = getAccountTypeResultMap();
        ENotifyUserType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的ENotifyUserType不存在");
        }
        return result;
    }

    ENotifyUserType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
