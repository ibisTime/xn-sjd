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
public enum EJourBizTypeAgent {

    // 人民币账户,代理方,养护方
    CHARGE("charge", "充值"),

    WITHDRAW("withdraw", "取现"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_FROZEN("withdraw_frozen", "取现冻结"),

    WITHDRAW_UNFROZEN("withdraw_unfrozen", "取现解冻"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    AGENT_DEDUCT("agent_deduct", "代理提成");

    public static EJourBizTypeAgent getBizType(String code) {
        Map<String, EJourBizTypeAgent> map = getBizTypeMap();
        EJourBizTypeAgent result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), code
                    + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeAgent> getBizTypeMap() {
        Map<String, EJourBizTypeAgent> map = new HashMap<String, EJourBizTypeAgent>();
        for (EJourBizTypeAgent bizType : EJourBizTypeAgent.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeAgent(String code, String value) {
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
