package com.ogc.standard.enums;

/**
 * 结算状态
 * @author: silver 
 * @since: Sep 29, 2018 5:21:24 PM 
 * @history:
 */
public enum ESettleStatus {

    TO_SETTLE("0", "待结算"), SETTLE_YES("1", "已结算"), SETTLE_NO("2", "不结算");

    ESettleStatus(String code, String value) {
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
