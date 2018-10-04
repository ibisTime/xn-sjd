package com.ogc.standard.enums;

public enum EUserRefereeType {
    USER("U", "普通用户"), AGENT("A", "代理商"), SALEMANS("S", "业务员");

    EUserRefereeType(String code, String value) {
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
