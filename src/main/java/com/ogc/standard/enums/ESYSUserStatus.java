package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

public enum ESYSUserStatus {

    TO_APPROVE("0", "待审核"), APPROVE_NO("1", "审核不通过"), PARTNER("2", "合伙中"), RELIEVE(
            "3", "已解除合伙"), LOGOUT("4", "已注销"), NORMAL("5", "正常");

    ESYSUserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ESYSUserStatus> getMap() {
        Map<String, ESYSUserStatus> map = new HashMap<String, ESYSUserStatus>();
        for (ESYSUserStatus userStatus : ESYSUserStatus.values()) {
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
