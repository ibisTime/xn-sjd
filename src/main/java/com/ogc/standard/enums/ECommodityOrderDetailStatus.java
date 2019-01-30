package com.ogc.standard.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum ECommodityOrderDetailStatus {

    TO_COMMENT("0", "待评价"),

    COMMENTED("1", "已评价"),

    AFTER_SELL_ING("2", "售后中"),

    AFTER_SALEED_YES("3", "退款成功"),

    TO_PAY("4", "待支付"),

    TODELIVE("5", "待发货"),

    TORECEIVE("6", "待收货")

    , CANCLED("7", "已取消"),

    AFTER_SALEED_NO("8", "退款失败"),

    AFTER_SALEED_CANCLED("9", "退款取消");

    ECommodityOrderDetailStatus(String code, String value) {
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
