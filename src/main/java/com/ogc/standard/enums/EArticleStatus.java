package com.ogc.standard.enums;

/**
 * 文章状态
 * @author: jiafr 
 * @since: 2018年10月2日 上午12:08:24 
 * @history:
 */
public enum EArticleStatus {
    DRAFT("1", "草稿"), TO_APPROVE("2", "待审核"), APPROVE_NO("3", "审核不通过"), TO_PUT_ON(
            "4", "待上架"), PUT_ON("5", "上架"), PUT_OFF("6", "下架");
    EArticleStatus(String code, String value) {
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
