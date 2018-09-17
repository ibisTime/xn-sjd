package com.ogc.standard.enums;

/**
 * @author: taojian 
 * @since: 2018年9月15日 下午9:40:33 
 * @history:
 */
public enum EAcceptType {
    IN("0", "买入"), OUT("1", "卖出");

    EAcceptType(String code, String value) {
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
