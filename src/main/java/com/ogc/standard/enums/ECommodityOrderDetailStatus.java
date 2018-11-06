package com.ogc.standard.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum ECommodityOrderDetailStatus {

    TODELIVE("0", "待发货"), TORECEIVE("1", "待收货"), FINISH("2", "已完成");

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
