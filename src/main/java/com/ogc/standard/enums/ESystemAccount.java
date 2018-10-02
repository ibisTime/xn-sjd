package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2017年2月9日 下午8:10:43 
 * @history:
 */
public enum ESystemAccount {

    SYS_ACOUNT_CNY("SYS_ACOUNT_CNY", "平台人民币盈亏账户")

    , SYS_ACOUNT_TPP("SYS_ACOUNT_TPP", "平台碳泡泡盈亏账户")

    , SYS_ACOUNT_JF("SYS_ACOUNT_JF", "平台积分盈亏账户")

    , SYS_ACOUNT_OFFLINE("SYS_ACOUNT_OFFLINE", "平台线下托管账户")

    , SYS_ACOUNT_WEIXIN("SYS_ACOUNT_WEIXIN", "平台微信托管账户")

    , SYS_ACOUNT_ALIPAY("SYS_ACOUNT_ALIPAY", "平台支付宝托管账户");

    public static Map<String, ESystemAccount> getMap() {
        Map<String, ESystemAccount> map = new HashMap<String, ESystemAccount>();
        for (ESystemAccount direction : ESystemAccount.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    ESystemAccount(String code, String value) {
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
