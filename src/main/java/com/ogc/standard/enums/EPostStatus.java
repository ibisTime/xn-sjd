package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 帖子状态
 * @author: silver 
 * @since: 2018年8月22日 下午3:02:54 
 * @history:
 */
public enum EPostStatus {

    TO_APPROVE("A", "待审核"), APPROVED_YES("B", "审核通过"), APPROVED_NO("C",
            "审核不通过"), RELEASED("D", "已发布"), DELETED("E", "已删除");

    EPostStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EPostStatus> getMap() {
        Map<String, EPostStatus> map = new HashMap<String, EPostStatus>();
        for (EPostStatus userStatus : EPostStatus.values()) {
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
