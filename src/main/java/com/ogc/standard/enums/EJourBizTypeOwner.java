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
public enum EJourBizTypeOwner {

    // 人民币账户,代理方,养护方
    CHARGE("charge", "充值"),

    WITHDRAW("withdraw", "取现"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_FROZEN("withdraw_frozen", "取现冻结"),

    WITHDRAW_UNFROZEN("withdraw_unfrozen", "取现解冻"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    OWNER_PROFIT("owner_profit", "产权收益");

    public static EJourBizTypeOwner getBizType(String code) {
        Map<String, EJourBizTypeOwner> map = getBizTypeMap();
        EJourBizTypeOwner result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), code
                    + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeOwner> getBizTypeMap() {
        Map<String, EJourBizTypeOwner> map = new HashMap<String, EJourBizTypeOwner>();
        for (EJourBizTypeOwner bizType : EJourBizTypeOwner.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeOwner(String code, String value) {
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
