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
public enum EToolStatus {
    UP("1", "已上架"), DOWN("1", "已下架");

    public static Map<String, EToolStatus> getToolStatusMap() {
        Map<String, EToolStatus> map = new HashMap<String, EToolStatus>();
        for (EToolStatus currency : EToolStatus.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EToolStatus getToolStatus(String code) {
        Map<String, EToolStatus> map = getToolStatusMap();
        EToolStatus result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的status不存在");
        }
        return result;
    }

    EToolStatus(String code, String value) {
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
