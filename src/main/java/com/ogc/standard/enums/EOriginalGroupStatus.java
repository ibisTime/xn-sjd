package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 预售原生组状态
 * @author: silver 
 * @since: Nov 3, 2018 7:29:33 PM 
 * @history:
 */
public enum EOriginalGroupStatus {

    TO_ADOPT("0", "待生效"), ADOPTING("1", "可转让"), TO_RECEIVE("2",
            "待提货"), RECEIVED("3", "已提货");

    EOriginalGroupStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EOriginalGroupStatus> getMap() {
        Map<String, EOriginalGroupStatus> map = new HashMap<String, EOriginalGroupStatus>();
        for (EOriginalGroupStatus accpetOrderStatus : EOriginalGroupStatus
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
