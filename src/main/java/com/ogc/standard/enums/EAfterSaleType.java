package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 售后类型
 * @author: taojian 
 * @since: 2018年11月7日 下午8:27:07 
 * @history:
 */
public enum EAfterSaleType {
    goodsMoney("1", "退货退款"), onlyMoney("0", "退款");

    public static Map<String, EAfterSaleType> getChannelTypeResultMap() {
        Map<String, EAfterSaleType> map = new HashMap<String, EAfterSaleType>();
        for (EAfterSaleType type : EAfterSaleType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    EAfterSaleType(String code, String value) {
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
