package com.ogc.standard.enums;

/**
 * 礼物订单状态
 * @author: jiafr 
 * @since: 2018年9月30日 上午11:05:39 
 * @history:
 */
public enum EGiftOrderStatus {

    TO_CLAIM("0", "待认领"), TO_SEND("1", "待发货"), FINSHED("2", "已完成"),

    EXPIRED("3", "已过期");

    EGiftOrderStatus(String code, String value) {
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
