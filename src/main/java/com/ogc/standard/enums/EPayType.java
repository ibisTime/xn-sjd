package com.ogc.standard.enums;

/**
 * 支付方式
 * @author: jiafr 
 * @since: 2018年9月29日 下午8:32:24 
 * @history:
 */
public enum EPayType {

    YE("1", "余额支付"),
    // WEIXIN_APP("2", "微信APP"),
    ALIPAY("3", "支付宝"), WEIXIN_H5("5", "微信h5");

    EPayType(String code, String value) {
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
