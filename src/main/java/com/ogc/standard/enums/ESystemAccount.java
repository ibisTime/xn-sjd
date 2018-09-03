package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2017年2月9日 下午8:10:43 
 * @history:
 */
public enum ESystemAccount {

    SYS_ACOUNT_ETH("SYS_ACOUNT_ETH",
            "平台ETH盈亏账户"), SYS_ACOUNT_ETH_COLD("SYS_ACOUNT_ETH_COLD", "平台ETH冷钱包")

    , SYS_ACOUNT_BTC("SYS_ACOUNT_BTC",
            "平台BTC盈亏账户"), SYS_ACOUNT_BTC_COLD("SYS_ACOUNT_BTC_COLD", "平台BTC冷钱包")

    , SYS_ACOUNT_WAN("SYS_ACOUNT_WAN", "平台WAN盈亏账户"), SYS_ACOUNT_WAN_COLD(
            "SYS_ACOUNT_WAN_COLD", "平台WAN冷钱包");

    public static Map<String, ESystemAccount> getMap() {
        Map<String, ESystemAccount> map = new HashMap<String, ESystemAccount>();
        for (ESystemAccount direction : ESystemAccount.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    public static String getPlatAccount(String symbol) {
        return "SYS_ACOUNT_" + symbol;
    }

    public static String getPlatColdAccount(String symbol) {
        return "SYS_ACOUNT_" + symbol + "_COLD";
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
