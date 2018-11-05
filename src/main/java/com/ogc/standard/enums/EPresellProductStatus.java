package com.ogc.standard.enums;

/**
 * 预售产品状态
 * @author: silver 
 * @since: Nov 2, 2018 6:45:49 PM 
 * @history:
 */
public enum EPresellProductStatus {

    DRAFT("0", "草稿"), TO_APPROVE("1", "已提交待审核"), APPROVE_NO("2", "审核不通过"), 
    TO_PUTON("3", "审核通过待上架"), TO_ADOPT("4", "已上架待购买"), PUTOFFED("5", "已下架");

    EPresellProductStatus(String code, String value) {
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
