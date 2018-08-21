package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 战队状态
 * @author: silver 
 * @since: 2018年8月21日 下午4:34:57 
 * @history:
 */
public enum ETeamStatus {

    TO_START("1", "待开始"), STARTED("2", "参赛中"), END("3", "已结束");

    ETeamStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ETeamStatus> getMap() {
        Map<String, ETeamStatus> map = new HashMap<String, ETeamStatus>();
        for (ETeamStatus userStatus : ETeamStatus.values()) {
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
