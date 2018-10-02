package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 
 * @author: lei 
 * @since: 2018年10月2日 下午8:03:24 
 * @history:
 */
public enum ECurrency {
    CNY("CNY", "人民币"), TPP("TPP", "碳泡泡"), JF("JF", "积分");

    public static Map<String, ECurrency> getCurrencyMap() {
        Map<String, ECurrency> map = new HashMap<String, ECurrency>();
        for (ECurrency currency : ECurrency.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static ECurrency getCurrency(String code) {
        Map<String, ECurrency> map = getCurrencyMap();
        ECurrency result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的currency不存在");
        }
        return result;
    }

    ECurrency(String code, String value) {
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
