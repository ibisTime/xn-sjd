package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 承兑交易订单状态
 * @author: lei 
 * @since: 2018年9月10日 下午8:53:33 
 * @history:
 */
public enum EAdoptOrderStatus {

    TO_PAY("0", "待支付"), PAYED("1", "已支付"), CANCELED("2", "已取消"), TO_ADOPT("3",
            "待认养"), ADOPT("4", "认养中"), END("5", "已到期");

    EAdoptOrderStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EAdoptOrderStatus> getMap() {
        Map<String, EAdoptOrderStatus> map = new HashMap<String, EAdoptOrderStatus>();
        for (EAdoptOrderStatus accpetOrderStatus : EAdoptOrderStatus.values()) {
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
