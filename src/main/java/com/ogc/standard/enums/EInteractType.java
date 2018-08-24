package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 点赞收藏类型
 * @author: silver 
 * @since: 2018年8月23日 上午10:17:53 
 * @history:
 */
public enum EInteractType {

    POINT("1", "点赞"), COLLECT("2", "收藏"), READ("3", "浏览");

    EInteractType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EInteractType> getMap() {
        Map<String, EInteractType> map = new HashMap<String, EInteractType>();
        for (EInteractType userStatus : EInteractType.values()) {
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
