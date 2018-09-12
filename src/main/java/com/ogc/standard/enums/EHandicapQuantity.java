package com.ogc.standard.enums;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午2:57:11 
 * @history:
 */
public enum EHandicapQuantity {

    FIFTY("50", "盘口总量50个"), SEVENTY("70", "盘口总量70个"), HUNDRED("100", "盘口总量70个");

    EHandicapQuantity(String code, String value) {
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
