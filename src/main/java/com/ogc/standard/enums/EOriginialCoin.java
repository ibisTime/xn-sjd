/**
 * @Title ECurrency.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午4:41:06 
 * @version V1.0   
 */
package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum EOriginialCoin {
    ETH("ETH", "以太币"), BTC("BTC", "比特币");

    public static Map<String, EOriginialCoin> getCurrencyMap() {
        Map<String, EOriginialCoin> map = new HashMap<String, EOriginialCoin>();
        for (EOriginialCoin currency : EOriginialCoin.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EOriginialCoin getCurrency(String code) {
        Map<String, EOriginialCoin> map = getCurrencyMap();
        EOriginialCoin result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的coin不存在");
        }
        return result;
    }

    EOriginialCoin(String code, String value) {
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
