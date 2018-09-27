package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 集体认养订单状态
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:54:52 
 * @history:
 */
public enum EGroupAdoptOrderStatus {

    TO_PAY("0", "待支付"), CANCELED("1", "已取消"), PAYED("2", "已支付"), FULL("3",
            "已满标"), UNFULL("4", "未满标已退钱"), ADOPT("5", "认养中"), END("6", "已到期");

    EGroupAdoptOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EGroupAdoptOrderStatus> getMap() {
        Map<String, EGroupAdoptOrderStatus> map = new HashMap<String, EGroupAdoptOrderStatus>();
        for (EGroupAdoptOrderStatus accpetOrderStatus : EGroupAdoptOrderStatus
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
