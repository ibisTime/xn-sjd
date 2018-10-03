package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 
 * @author: lei 
 * @since: 2018年10月2日 下午8:05:46 
 * @history:
 */
public enum EToolOrderStatus {
    TO_USE("0", "未使用"), USED("1", "已使用");

    public static Map<String, EToolOrderStatus> getToolOrderStatusMap() {
        Map<String, EToolOrderStatus> map = new HashMap<String, EToolOrderStatus>();
        for (EToolOrderStatus currency : EToolOrderStatus.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EToolOrderStatus getToolOrderStatus(String code) {
        Map<String, EToolOrderStatus> map = getToolOrderStatusMap();
        EToolOrderStatus result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的status不存在");
        }
        return result;
    }

    EToolOrderStatus(String code, String value) {
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
