package com.ogc.standard.enums;

/**
 * 文章类型
 * @author: jiafr 
 * @since: 2018年10月2日 上午12:13:38 
 * @history:
 */
public enum EArticleType {

    PLAT("1", "平台"), USER("2", "用户");

    EArticleType(String code, String value) {
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
