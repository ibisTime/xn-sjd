package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 预售派生组状态
 * @author: silver 
 * @since: Nov 4, 2018 1:27:19 PM 
 * @history:
 */
public enum EDeriveGroupStatus {

    TO_CLAIM("0", "待认领"), CLAIMED("1", "已认领"), CANCELED("2",
            "已撤销"), REJECTED("3", "已拒绝");

    EDeriveGroupStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EDeriveGroupStatus> getMap() {
        Map<String, EDeriveGroupStatus> map = new HashMap<String, EDeriveGroupStatus>();
        for (EDeriveGroupStatus accpetOrderStatus : EDeriveGroupStatus
            .values()) {
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
