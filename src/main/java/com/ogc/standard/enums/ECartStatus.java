package com.ogc.standard.enums;

/**
 * 购物车状态
 * @author: silver 
 * @since: Jan 17, 2019 11:13:19 AM 
 * @history:
 */
public enum ECartStatus {

    UN_VALID("0", "失效"), VALID("1", "有效");

    ECartStatus(String code, String value) {
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
