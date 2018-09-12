package com.ogc.standard.enums;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午2:57:11 
 * @history:
 */
public enum EHandicapGradeNum {

    FIVE("5", "5档"), SEVEN("7", "7档"), TEN("10", "10档");

    EHandicapGradeNum(String code, String value) {
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
