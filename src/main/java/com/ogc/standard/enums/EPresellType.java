package com.ogc.standard.enums;

/**
 * 寄售类型
 * @author: silver 
 * @since: Nov 4, 2018 1:23:39 PM 
 * @history:
 */
public enum EPresellType {

    DIRECT("0", "定向"), QR("1", "二维码"), PUBLIC("2", "挂单");

    EPresellType(String code, String value) {
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
