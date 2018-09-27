package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人捐赠定向认养订单状态
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:54:31 
 * @history:
 */
public enum EAdoptOrderStatus {

    TO_PAY("0", "待支付"), CANCELED("1", "已取消"), TO_ADOPT("2", "待认养"), ADOPT("3",
            "认养中"), END("4", "已到期");

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
