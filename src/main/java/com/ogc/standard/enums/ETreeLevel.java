package com.ogc.standard.enums;

/**
 * 树木级别
 * @author: silver 
 * @since: Jan 24, 2019 10:57:46 AM 
 * @history:
 */
public enum ETreeLevel {

    FIRST("FIRST", "一级"),

    SECOND("SECOND", "二级"),

    THIRD("THIRD", "三级");

    ETreeLevel(String code, String value) {
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
