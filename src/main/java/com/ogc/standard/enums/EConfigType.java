package com.ogc.standard.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum EConfigType {

    QINIU("qiniu", "七牛"), TENCENT_IM("tencent_im", "腾讯IM"), USER("user_rate",
            "用户广告费率"), SIMU_ORDER("simu_order_rate", "币币交易手续费");

    EConfigType(String code, String value) {
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
