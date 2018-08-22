package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 关键字反应
 * @author: silver 
 * @since: 2018年8月22日 下午11:27:05 
 * @history:
 */
public enum EKeyWordReaction {

    REFUSE("1", "拦截"), REPLACE("2", "替换**"), APPROVE("3", "审核");

    EKeyWordReaction(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EKeyWordReaction> getMap() {
        Map<String, EKeyWordReaction> map = new HashMap<String, EKeyWordReaction>();
        for (EKeyWordReaction userStatus : EKeyWordReaction.values()) {
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
