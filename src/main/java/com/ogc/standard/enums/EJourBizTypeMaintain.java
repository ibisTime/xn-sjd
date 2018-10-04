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
public enum EJourBizTypeMaintain {

    // 人民币账户,代理方,养护方
    CHARGE("charge", "充值"),

    WITHDRAW("withdraw", "取现"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_FROZEN("withdraw_frozen", "取现冻结"),

    WITHDRAW_UNFROZEN("withdraw_unfrozen", "取现解冻"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    MAINTAIN_DEDECT("maintain_deduct", "养护提成");

    public static EJourBizTypeMaintain getBizType(String code) {
        Map<String, EJourBizTypeMaintain> map = getBizTypeMap();
        EJourBizTypeMaintain result = map.get(code);
        if (result == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), code
                    + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeMaintain> getBizTypeMap() {
        Map<String, EJourBizTypeMaintain> map = new HashMap<String, EJourBizTypeMaintain>();
        for (EJourBizTypeMaintain bizType : EJourBizTypeMaintain.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeMaintain(String code, String value) {
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
