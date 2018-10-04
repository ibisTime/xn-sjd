package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum ESystemCode {
    BZ("CD-SJD0000025", "时间岛");

    public static Map<String, ESystemCode> getCurrencyMap() {
        Map<String, ESystemCode> map = new HashMap<String, ESystemCode>();
        for (ESystemCode currency : ESystemCode.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    ESystemCode(String code, String value) {
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
