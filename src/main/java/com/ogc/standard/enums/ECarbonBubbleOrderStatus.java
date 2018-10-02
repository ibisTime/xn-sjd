package com.ogc.standard.enums;

/**
 * 碳泡泡产生订单状态
 * @author: jiafr 
 * @since: 2018年10月2日 下午3:17:13 
 * @history:
 */
public enum ECarbonBubbleOrderStatus {

    TO_TAKE("0", "待收取"), TAKED("1", "已收取"), INVALID("2", "已过期");

    ECarbonBubbleOrderStatus(String code, String value) {
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
