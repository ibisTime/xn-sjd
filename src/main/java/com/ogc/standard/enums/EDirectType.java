package com.ogc.standard.enums;

public enum EDirectType {
    LEVEL("1", "一个等级"), ONE_USER("2", "一个人");

    EDirectType(String code, String value) {
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
