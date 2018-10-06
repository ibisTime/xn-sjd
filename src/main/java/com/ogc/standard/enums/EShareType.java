package com.ogc.standard.enums;

/**
 * 分享类型
 * @author: silver 
 * @since: Oct 6, 2018 5:57:06 PM 
 * @history:
 */
public enum EShareType {
    WEIXIN("1", "微信"), WEIBO("2", "微博");

    EShareType(String code, String value) {
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
