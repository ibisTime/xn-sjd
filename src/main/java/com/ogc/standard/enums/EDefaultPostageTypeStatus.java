package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认邮费类型
 * @author: silver 
 * @since: Dec 5, 2018 2:35:54 PM 
 * @history:
 */
public enum EDefaultPostageTypeStatus {

    SAME_PROVINCE("0", "同省"), DIF_PROVINCE("1", "跨省");

    EDefaultPostageTypeStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EDefaultPostageTypeStatus> getMap() {
        Map<String, EDefaultPostageTypeStatus> map = new HashMap<String, EDefaultPostageTypeStatus>();
        for (EDefaultPostageTypeStatus accpetOrderStatus : EDefaultPostageTypeStatus
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
