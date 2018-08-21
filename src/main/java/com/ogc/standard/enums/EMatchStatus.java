package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 赛事状态
 * @author: silver 
 * @since: 2018年8月21日 上午10:52:02 
 * @history:
 */
public enum EMatchStatus {

    TO_PUBLISH("1", "待发布"), PUBLISHED("2", "已发布"), STARTED("3",
            "已开始"), EXPIRED("4", "已过期");

    EMatchStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EMatchStatus> getMap() {
        Map<String, EMatchStatus> map = new HashMap<String, EMatchStatus>();
        for (EMatchStatus userStatus : EMatchStatus.values()) {
            map.put(userStatus.getCode(), userStatus);
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
