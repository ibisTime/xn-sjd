package com.ogc.standard.enums;

/**
 * 产品分类状态
 * @author: silver 
 * @since: 2018年9月26日 下午1:53:37 
 * @history:
 */
public enum ECategoryStatus {

    PUT_OFF("0", "下架"), PUT_ON("1", "上架");

    ECategoryStatus(String code, String value) {
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
