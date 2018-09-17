package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypeCold {

    AJ_DEPOSIT("deposit", "定存"), AJ_COLLECT("collect", "归集");

    public static EJourBizTypeCold getBizType(String code) {
        Map<String, EJourBizTypeCold> map = getBizTypeMap();
        EJourBizTypeCold result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeCold> getBizTypeMap() {
        Map<String, EJourBizTypeCold> map = new HashMap<String, EJourBizTypeCold>();
        for (EJourBizTypeCold bizType : EJourBizTypeCold.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeCold(String code, String value) {
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
