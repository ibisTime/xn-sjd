package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 
 * @author: lei 
 * @since: 2018年10月2日 下午8:05:46 
 * @history:
 */
public enum EToolType {
    SHIELD("0", "保护罩"), GET_ALL("1", "一件收取");

    public static Map<String, EToolType> getToolTypeMap() {
        Map<String, EToolType> map = new HashMap<String, EToolType>();
        for (EToolType currency : EToolType.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EToolType getToolType(String code) {
        Map<String, EToolType> map = getToolTypeMap();
        EToolType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的type不存在");
        }
        return result;
    }

    EToolType(String code, String value) {
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
