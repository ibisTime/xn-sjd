package com.ogc.standard.enums;

/**
 * 产品销售类型
 * @author: silver 
 * @since: 2018年9月27日 上午10:05:20 
 * @history:
 */
public enum ESellType {

    PERSON("0", "个人"), DIRECT("1", "定向"), DONATE("2", "捐赠"), COLLECTIVE("3",
            "集体");

    ESellType(String code, String value) {
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
