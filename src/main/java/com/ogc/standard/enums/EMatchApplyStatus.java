package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 参赛申请状态
 * @author: silver 
 * @since: 2018年8月21日 下午2:42:49 
 * @history:
 */
public enum EMatchApplyStatus {

    TO_APPROVE("1", "待审核"), APPROVED_YES("2", "审核通过"), APPROVED_NO("3",
            "审核不通过");

    EMatchApplyStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EMatchApplyStatus> getMap() {
        Map<String, EMatchApplyStatus> map = new HashMap<String, EMatchApplyStatus>();
        for (EMatchApplyStatus userStatus : EMatchApplyStatus.values()) {
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
