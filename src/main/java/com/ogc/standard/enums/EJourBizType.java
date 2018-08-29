package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * 
 * @author: lei 
 * @since: 2018年8月23日 下午10:04:20 
 * @history:
 */
public enum EJourBizType {
    BUY_ORDER_FROZEN("buy_order_frozen", "提交买入委托单冻结"),

    BUY_ORDER_UNFROZEN("buy_order_unfrozen", "买入委托单解冻"),

    BUY_ORDER_SUCCESS("buy_order_success", "买入委托单成交"),

    SELL_ORDER_FROZEN("buy_order_frozen", "提交卖出委托单冻结"),

    SELL_ORDER_UNFROZEN("buy_order_unfrozen", "卖出委托单解冻"),

    SELL_ORDER_SUCCESS("sell_order_success", "卖出委托单成交"),

    DISTRIBUTE_ACCOUNT("distribute_account", "分配账户初始币种");

    public static EJourBizType getBizType(String code) {
        Map<String, EJourBizType> map = getBizTypeMap();
        EJourBizType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizType> getBizTypeMap() {
        Map<String, EJourBizType> map = new HashMap<String, EJourBizType>();
        for (EJourBizType bizType : EJourBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizType(String code, String value) {
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
