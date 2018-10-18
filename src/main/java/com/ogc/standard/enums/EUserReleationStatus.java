package com.ogc.standard.enums;

/**
 * 用户关系状态
 * @author: silver 
 * @since: Oct 18, 2018 5:03:05 PM 
 * @history:
 */
public enum EUserReleationStatus {

    NORMAL("1", "正常"), DELETED("0", "删除");

    EUserReleationStatus(String code, String value) {
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
