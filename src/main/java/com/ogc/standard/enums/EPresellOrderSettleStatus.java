package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 预售结算状态
 * @author: silver 
 * @since: Nov 3, 2018 4:14:05 PM 
 * @history:
 */
public enum EPresellOrderSettleStatus {

    NO_SETTLE("0", "不结算"), TO_SETTLE("1", "待结算"), SETTLE("2", "已结算");

    EPresellOrderSettleStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EPresellOrderSettleStatus> getMap() {
        Map<String, EPresellOrderSettleStatus> map = new HashMap<String, EPresellOrderSettleStatus>();
        for (EPresellOrderSettleStatus status : EPresellOrderSettleStatus
            .values()) {
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
