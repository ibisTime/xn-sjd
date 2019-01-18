package com.ogc.standard.enums;

/**
 * 认养权类型
 * @author: silver 
 * @since: Jan 18, 2019 11:48:38 AM 
 * @history:
 */
public enum EAdoptOrderTreeType {
    PERSON("1", "个人"), DIRECT("2", "定向"), DONATE("3", "捐赠"), COLLECTIVE("4",
            "集体"), PRESELL("5", "预售"), CONSIGN("6", "寄售"), GIVE("7", "赠送");

    EAdoptOrderTreeType(String code, String value) {
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
