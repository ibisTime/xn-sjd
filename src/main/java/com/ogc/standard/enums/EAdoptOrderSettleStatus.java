package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人捐赠定向认养结算状态
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:54:31 
 * @history:
 */
public enum EAdoptOrderSettleStatus {

    NO_SETTLE("0", "不结算"), TO_SETTLE("1", "待结算"), SETTLE("2", "已结算");

    EAdoptOrderSettleStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EAdoptOrderSettleStatus> getMap() {
        Map<String, EAdoptOrderSettleStatus> map = new HashMap<String, EAdoptOrderSettleStatus>();
        for (EAdoptOrderSettleStatus status : EAdoptOrderSettleStatus.values()) {
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
