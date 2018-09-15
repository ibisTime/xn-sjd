package com.ogc.standard.enums;

/** 
 * 
 * @author: lei 
 * @since: 2018年9月15日 上午10:57:21 
 * @history:
 */
public enum EDivideStatus {
    TO_DIVIDE("1", "待分红"), DIVIDED("0", "已分红");

    EDivideStatus(String code, String value) {
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
