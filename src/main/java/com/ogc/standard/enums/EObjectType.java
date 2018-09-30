package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象类型
 * @author: silver 
 * @since: 2018年8月23日 上午10:09:30 
 * @history:
 */
public enum EObjectType {

    TREE("1", "树木"), ARTICLE("2", "文章");

    EObjectType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EObjectType> getMap() {
        Map<String, EObjectType> map = new HashMap<String, EObjectType>();
        for (EObjectType userStatus : EObjectType.values()) {
            map.put(userStatus.getCode(), userStatus);
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
