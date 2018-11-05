package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 预售订单状态
 * @author: silver 
 * @since: Nov 3, 2018 3:56:41 PM 
 * @history:
 */
public enum EPresellOrderStatus {

    TO_PAY("0", "待支付"), CANCELED("1", "已取消"), PAYED("2", "已支付");

    EPresellOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EPresellOrderStatus> getMap() {
        Map<String, EPresellOrderStatus> map = new HashMap<String, EPresellOrderStatus>();
        for (EPresellOrderStatus accpetOrderStatus : EPresellOrderStatus
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
