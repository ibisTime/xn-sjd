package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 评论状态
 * @author: silver 
 * @since: 2018年8月22日 下午8:33:50 
 * @history:
 */
public enum ECommentStatus {

    TO_APPROVE("A", "待审核"), APPROVED_YES("B", "审核通过"), APPROVED_NO("C",
            "审核不通过"), RELEASED("D", "已发布"), DELETED("E", "已删除");

    ECommentStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ECommentStatus> getMap() {
        Map<String, ECommentStatus> map = new HashMap<String, ECommentStatus>();
        for (ECommentStatus userStatus : ECommentStatus.values()) {
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
