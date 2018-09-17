package com.ogc.standard.enums;

public enum ESAddressStatus {
    VALID("0", "可使用"), IN_USE("1", "广播使用中"), INVALID("2", "已弃用");

    ESAddressStatus(String code, String value) {
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
