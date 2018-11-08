package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 帖子过滤标志
 * @author: silver 
 * @since: 2018年8月24日 上午10:34:47 
 * @history:
 */
public enum EFilterFlag {

    NORMAN("0", "未过滤"), REPLACED("1", "关键字被替换"), TO_APPROVE("2", "待审核");

    EFilterFlag(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, EFilterFlag> getMap() {
        Map<String, EFilterFlag> map = new HashMap<String, EFilterFlag>();
        for (EFilterFlag userStatus : EFilterFlag.values()) {
            map.put(userStatus.getCode(), userStatus);
        }
        return map;
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
