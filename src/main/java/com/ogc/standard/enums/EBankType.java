package com.ogc.standard.enums;

/**
 * 
 * @author: lei 
 * @since: 2018年8月21日 下午5:25:45 
 * @history:
 */
public enum EBankType {
    BANKCARD("0", "银行卡"), ALI_PAY("1", "支付宝");

    EBankType(String code, String value) {
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
