package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 弹幕状态
 * @author: silver 
 * @since: Nov 23, 2018 3:08:26 PM 
 * @history:
 */
public enum EBarrageStatus {
    UP("1", "已上架"), DOWN("0", "已下架");

    public static Map<String, EBarrageStatus> getToolStatusMap() {
        Map<String, EBarrageStatus> map = new HashMap<String, EBarrageStatus>();
        for (EBarrageStatus currency : EBarrageStatus.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EBarrageStatus getToolStatus(String code) {
        Map<String, EBarrageStatus> map = getToolStatusMap();
        EBarrageStatus result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的status不存在");
        }
        return result;
    }

    EBarrageStatus(String code, String value) {
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
