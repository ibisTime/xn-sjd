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
public enum EToolUseRecordStatus {
    INVALID("0", "已失效"), ACTIVE("1", "生效中");

    public static Map<String, EToolUseRecordStatus> getToolUseRecordStatusMap() {
        Map<String, EToolUseRecordStatus> map = new HashMap<String, EToolUseRecordStatus>();
        for (EToolUseRecordStatus currency : EToolUseRecordStatus.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static EToolUseRecordStatus getToolUseRecordStatus(String code) {
        Map<String, EToolUseRecordStatus> map = getToolUseRecordStatusMap();
        EToolUseRecordStatus result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的status不存在");
        }
        return result;
    }

    EToolUseRecordStatus(String code, String value) {
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
