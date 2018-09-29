package com.ogc.standard.enums;

/**
 * 订单类型（1个人/2定向/3捐赠）
 * @author: jiafr 
 * @since: 2018年9月29日 下午5:24:47 
 * @history:
 */
public enum EOrderKind {

    person("1", "正常"), ORIENT("2", "定向"), DONATE("3", "捐赠");

    EOrderKind(String code, String value) {
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
