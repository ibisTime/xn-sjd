package com.ogc.standard.enums;

/**
 * 文章权限 公开程度
 * @author: jiafr 
 * @since: 2018年10月2日 上午1:19:59 
 * @history:
 */
public enum EArticleRight {

    OPEN("1", "公开"), PRIVACY("2", "私密"), ONLY_FRIEND("3", "仅好友可见");

    EArticleRight(String code, String value) {
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
