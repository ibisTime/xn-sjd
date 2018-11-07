package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 寄售订单状态
 * @author: silver 
 * @since: Nov 3, 2018 3:56:41 PM 
 * @history:
 */
public enum EGroupOrderStatus {

    TO_PAY("0", "待支付"), CANCELED("1", "已取消"), PAYED("2", "已支付");

    EGroupOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EGroupOrderStatus> getMap() {
        Map<String, EGroupOrderStatus> map = new HashMap<String, EGroupOrderStatus>();
        for (EGroupOrderStatus accpetOrderStatus : EGroupOrderStatus.values()) {
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
