package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/**
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午7:53:51 
 * @history:
 */
public enum ESimuOrderType {

    MARKET("0", "市价"), LIMIT("1", "限价");

    public static Map<String, ESimuOrderType> getOrderTypeMap() {
        Map<String, ESimuOrderType> map = new HashMap<String, ESimuOrderType>();
        for (ESimuOrderType type : ESimuOrderType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static ESimuOrderType getOrderType(String code) {
        Map<String, ESimuOrderType> map = getOrderTypeMap();
        ESimuOrderType result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                code + "对应的委托类型不存在");
        }
        return result;
    }

    ESimuOrderType(String code, String value) {
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
