package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/**
 * 
 * @author: lei 
 * @since: 2018年8月21日 下午5:25:45 
 * @history:
 */
public enum ECoin {
    ETH("ETH", "以太币"), BTC("BTC", "比特币"), USDT("USDT", "美元代币"), SC("SC",
            "云储币"), HPM("HPM", "开心币");

    public static Map<String, ECoin> getCurrencyMap() {
        Map<String, ECoin> map = new HashMap<String, ECoin>();
        for (ECoin currency : ECoin.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static ECoin getCoin(String code) {
        Map<String, ECoin> map = getCurrencyMap();
        ECoin result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                code + "对应的coin不存在");
        }
        return result;
    }

    ECoin(String code, String value) {
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
