package com.ogc.standard.enums;

/**
 * 代理等级
 * @author: xieyj 
 * @since: 2018年10月3日 下午2:26:22 
 * @history:
 */
public enum EAgentUserLevel {
    FIRST("1", "一级代理"), SECOND("2", "二级代理"), THREE("3", "三级代理"), FOUR("4",
            "四级代理");

    EAgentUserLevel(String code, String value) {
        this.code = code;
        this.value = value;
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
