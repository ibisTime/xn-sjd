package com.ogc.standard.enums;

public enum EWAddressStatus {
    VALID("0", "正常"), INVALID("2", "已弃用");

    EWAddressStatus(String code, String value) {
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
