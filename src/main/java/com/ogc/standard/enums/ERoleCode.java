package com.ogc.standard.enums;

/**
 * 
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:35:24 
 * @history:
 */
public enum ERoleCode {

    OWNER("JS201809301134241553541", "产权端"), MAINTAIN(
            "JS201809301134504008291", "养护端");

    ERoleCode(String code, String value) {
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
