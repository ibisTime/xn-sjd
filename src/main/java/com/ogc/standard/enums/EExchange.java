package com.ogc.standard.enums;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:17:42 
 * @history:
 */
public enum EExchange {

    HuobiPro("huobiPro", "火币Pro");

    EExchange(String code, String value) {
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
