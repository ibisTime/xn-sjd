package com.ogc.standard.enums;

/** 
 * @author: xieyj 
 * @since: 2015-3-7 上午8:41:50 
 * @history:
 */
public enum ESysConfigType {

    QINIU("qiniu", "七牛"), TENCENT_IM("tencent_im", "腾讯IM"), ADS_USER(
            "ads_user_rate", "用户广告费率"), ACCEPT_RULE("accept_rule", "承兑商规则");

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
