package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 集体认养结算状态
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:54:31 
 * @history:
 */
public enum EGroupAdoptOrderSettleStatus {

    NO_SETTLE("0", "不结算"), TO_SETTLE("1", "待结算"), SETTLE("2", "已结算");

    EGroupAdoptOrderSettleStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EGroupAdoptOrderSettleStatus> getMap() {
        Map<String, EGroupAdoptOrderSettleStatus> map = new HashMap<String, EGroupAdoptOrderSettleStatus>();
        for (EGroupAdoptOrderSettleStatus status : EGroupAdoptOrderSettleStatus
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
