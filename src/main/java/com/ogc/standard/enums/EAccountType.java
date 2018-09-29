package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum EAccountType {
    CUSTOMER("C", "C端账号"), OWNER("O", "产权方账号"), MAINTAIN("M", "养护方账号"), AGENT(
            "A", "代理商账号"), PLAT("P", "平台账号");

    public static Map<String, EAccountType> getAccountTypeResultMap() {
        Map<String, EAccountType> map = new HashMap<String, EAccountType>();
        for (EAccountType type : EAccountType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EAccountType getAccountType(String code) {
        Map<String, EAccountType> map = getAccountTypeResultMap();
        EAccountType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的accountType不存在");
        }
        return result;
    }

    EAccountType(String code, String value) {
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
