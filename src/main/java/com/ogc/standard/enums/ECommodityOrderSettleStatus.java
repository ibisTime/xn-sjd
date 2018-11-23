package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品订单结算状态
 * @author: silver 
 * @since: Nov 22, 2018 3:43:28 PM 
 * @history:
 */
public enum ECommodityOrderSettleStatus {

    NO_SETTLE("0", "不结算"), TO_SETTLE("1", "待结算"), SETTLE("2", "已结算");

    ECommodityOrderSettleStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ECommodityOrderSettleStatus> getMap() {
        Map<String, ECommodityOrderSettleStatus> map = new HashMap<String, ECommodityOrderSettleStatus>();
        for (ECommodityOrderSettleStatus status : ECommodityOrderSettleStatus
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
