package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 承兑交易订单状态
 * @author: lei 
 * @since: 2018年9月10日 下午8:53:33 
 * @history:
 */
public enum ECoinAcceptOrderStatus {

    TO_PAY("0", "待支付"), PAYED("1", "已支付"), RELEASED("2", "已释放"), CANCELED("3",
            "已取消");

    ECoinAcceptOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ECoinAcceptOrderStatus> getMap() {
        Map<String, ECoinAcceptOrderStatus> map = new HashMap<String, ECoinAcceptOrderStatus>();
        for (ECoinAcceptOrderStatus accpetOrderStatus : ECoinAcceptOrderStatus
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
