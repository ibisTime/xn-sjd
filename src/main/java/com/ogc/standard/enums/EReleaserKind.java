package com.ogc.standard.enums;

/**
 * 订单释放者类型
 * @author: taojian 
 * @since: 2018年9月5日 下午9:37:47 
 * @history:
 */
public enum EReleaserKind {
    seller("S", "卖方"), plat("P", "平台");

    EReleaserKind(String code, String value) {
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
