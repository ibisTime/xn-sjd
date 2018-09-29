package com.ogc.standard.enums;

/**
 * 支付方式
 * @author: jiafr 
 * @since: 2018年9月29日 下午8:32:24 
 * @history:
 */
public enum EPayType {

    BALANCE("1", "余额支付"), ALI_PAY("2", "支付宝"), WECHAT("3", "微信"), BANK_CARD(
            "4", "银行卡");

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
