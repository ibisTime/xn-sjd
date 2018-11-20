package com.ogc.standard.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum ECommodityOrderStatus {

    TO_PAY("0", "待支付"), TODELIVE("1", "待发货"), TORECEIVE("2", "待收货"), TO_COMMENT(
            "3", "待评价"), FINISH("4", "已完成"), CANCLED("5", "已取消");

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
