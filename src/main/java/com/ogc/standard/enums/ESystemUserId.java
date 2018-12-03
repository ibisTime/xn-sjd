package com.ogc.standard.enums;

/**
 * 系统用户编号
 * @author: silver 
 * @since: Nov 30, 2018 11:21:38 AM 
 * @history:
 */
public enum ESystemUserId {
    ADMIN("UCOIN201700000000000001", "系统管理员");

    ESystemUserId(String code, String value) {
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
