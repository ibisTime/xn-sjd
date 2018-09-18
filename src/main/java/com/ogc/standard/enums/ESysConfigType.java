package com.ogc.standard.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ESysConfigType {

    QINIU("qiniu", "七牛"), TENCENT_IM("tencent_im", "腾讯IM"), ADS_RATE("ads_rule",
            "场外交易规则"), SIMU_ORDER_RATE("simu_order_rule",
                    "币币交易规则"), WITHDRAW_RATE("withdraw_rule",
                            "提币规则"), ACCEPT_RULE("accept_rule", "承兑商规则");

    ESysConfigType(String code, String value) {
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
