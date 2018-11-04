package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 预售物流单状态
 * @author: silver 
 * @since: Nov 3, 2018 3:56:41 PM 
 * @history:
 */
public enum EPresellLogisticsStatus {

    TO_SEND("0", "待发货"), TO_RECEIVE("1", "待收货"), RECEIVED("2", "已收货");

    EPresellLogisticsStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EPresellLogisticsStatus> getMap() {
        Map<String, EPresellLogisticsStatus> map = new HashMap<String, EPresellLogisticsStatus>();
        for (EPresellLogisticsStatus accpetOrderStatus : EPresellLogisticsStatus
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
