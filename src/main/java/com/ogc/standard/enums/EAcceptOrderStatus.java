package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 承兑交易订单状态
 * @author: lei 
 * @since: 2018年9月10日 下午8:53:33 
 * @history:
 */
public enum EAcceptOrderStatus {

    TO_PAY("0", "待支付"), PAYED("1", "已支付"), RELEASED("2", "已释放"), CCANCELED("3",
            "用户取消"), PCANCELED("4", "平台取消");

    EAcceptOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EAcceptOrderStatus> getMap() {
        Map<String, EAcceptOrderStatus> map = new HashMap<String, EAcceptOrderStatus>();
        for (EAcceptOrderStatus accpetOrderStatus : EAcceptOrderStatus
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
