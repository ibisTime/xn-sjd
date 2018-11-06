package com.ogc.standard.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum ECommodityOrderStatus {

    TOPAY("0", "代支付"), PAIED("1", "已支付"), CANCEL("2", "已取消");

    ECommodityOrderStatus(String code, String value) {
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
