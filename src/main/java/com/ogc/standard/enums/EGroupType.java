package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 组类型
 * @author: silver 
 * @since: Nov 3, 2018 7:56:03 PM 
 * @history:
 */
public enum EGroupType {

    ORIGINAL_GROUP("0", "原生组"), DERIVE_GROUP("1", "派生组");

    EGroupType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EGroupType> getMap() {
        Map<String, EGroupType> map = new HashMap<String, EGroupType>();
        for (EGroupType accpetOrderStatus : EGroupType.values()) {
            map.put(accpetOrderStatus.getCode(), accpetOrderStatus);
        }
        return map;
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
