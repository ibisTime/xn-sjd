package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户关系状态
 * @author: silver 
 * @since: Dec 6, 2018 4:14:46 PM 
 * @history:
 */
public enum EUserRelationStatus {

    TO_APPROVE("0", "待审核"), APPROVE_YES("1", "审核通过"), APPROVE_NO("2", "审核不通过");

    EUserRelationStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EUserRelationStatus> getMap() {
        Map<String, EUserRelationStatus> map = new HashMap<String, EUserRelationStatus>();
        for (EUserRelationStatus status : EUserRelationStatus.values()) {
            map.put(status.getCode(), status);
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
