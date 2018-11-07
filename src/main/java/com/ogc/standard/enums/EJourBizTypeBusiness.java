package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/**
 * @author: xieyj 
 * @since: 2018年10月2日 下午10:52:13 
 * @history:
 */
public enum EJourBizTypeBusiness {

    // 人民币账户,商家
    CHARGE("charge", "充值"),

    WITHDRAW("withdraw", "取现"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_FROZEN("withdraw_frozen", "取现冻结"),

    WITHDRAW_UNFROZEN("withdraw_unfrozen", "取现解冻"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    AFTER_SALE("after_sale", "商城售后"),

    BUSINESS_PROFIT("business_profit", "商城收益");

    public static EJourBizTypeBusiness getBizType(String code) {
        Map<String, EJourBizTypeBusiness> map = getBizTypeMap();
        EJourBizTypeBusiness result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), code
                    + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeBusiness> getBizTypeMap() {
        Map<String, EJourBizTypeBusiness> map = new HashMap<String, EJourBizTypeBusiness>();
        for (EJourBizTypeBusiness bizType : EJourBizTypeBusiness.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeBusiness(String code, String value) {
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
