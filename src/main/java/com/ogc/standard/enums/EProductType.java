package com.ogc.standard.enums;

/**
 * 产品类型
 * @author: silver 
 * @since: 2018年9月26日 下午8:15:22 
 * @history:
 */
public enum EProductType {

    NORMAL("0", "正常产品"), YS("1", "预售产品");

    EProductType(String code, String value) {
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
