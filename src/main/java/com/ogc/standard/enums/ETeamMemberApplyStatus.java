package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 战队成员申请状态
 * @author: silver 
 * @since: 2018年8月21日 下午7:08:55 
 * @history:
 */
public enum ETeamMemberApplyStatus {

    TO_APPROVE("1", "待审核"), APPROVED_YES("2", "审核通过"), APPROVED_NO("3",
            "审核不通过");

    ETeamMemberApplyStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ETeamMemberApplyStatus> getMap() {
        Map<String, ETeamMemberApplyStatus> map = new HashMap<String, ETeamMemberApplyStatus>();
        for (ETeamMemberApplyStatus userStatus : ETeamMemberApplyStatus
            .values()) {
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
