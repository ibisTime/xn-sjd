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
public enum EJourBizTypeUser2 {

    AJ_CHARGE("charge", "充币"), 
    AJ_WITHDRAW("withdraw", "提币"), 
    AJ_WITHDRAW_FEE("withdraw_fee", "提币手续费"),
    AJ_WITHDRAW_FROZEN("withdraw_frozen", "提币冻结"),
    AJ_WITHDRAW_UNFROZEN("withdraw_unfrozen", "提币解冻"),

    AJ_ACCPET_BUY("accept_buy", "场外承兑商购买"), 
    AJ_ACCPET_SELL("accept_sell","场外承兑商出售"), 
    AJ_ACCPET_FEE("accept_fee", "场外承兑商手续费"),
    AJ_ACCPET_FROZEN("withdraw_frozen", "场外承兑商冻结"),
    AJ_ACCPET_UNFROZEN("withdraw_unfrozen", "场外承兑商解冻"),
    
    AJ_CCORDER_BUY("ccorder_buy", "场外cc买入"), 
    AJ_CCORDER_SELL("ccorder_sell", "场外cc卖出"), 
    AJ_CCORDER_FEE("ccorder_fee", "场外cc手续费"),
    AJ_CCORDER_FROZEN("ccorder_frozen", "场外cc冻结"),
    AJ_CCORDER_UNFROZEN_REVOKE("ccorder_unfrozen_revoke", "场外cc撤单解冻"),
    AJ_CCORDER_UNFROZEN_TRADE("ccorder_unfrozen_trade", "场外cc成交解冻"),

    AJ_BBORDER_BUY("bborder_buy", "币币交易兑入"), 
    AJ_BBORDER_SELL("bborder_sell", "币币交易兑出"), 
    AJ_BBORDER_FEE("bborder_fee", "币币交易手续费"),
    AJ_BBORDER_FROZEN("bborder_frozen", "币币交易冻结"),
    AJ_BBORDER_UNFROZEN_REVOKE("bborder_unfrozen_revoke", "币币撤单解冻"),
    AJ_BBORDER_UNFROZEN_TRADE("bborder_unfrozen_trade", "币币交易解冻"),

    AJ_HC("hc", "红冲"), 
    AJ_LB("lb", "蓝补"),
    
    AJ_AWARD("award", "奖励"), 
    AJ_DIVIDE("divide", "分红"),

    AJ_GAME_IN("game_in", "游戏转入"), // 转账记录用type区分类型
    AJ_GAME_OUT("game_out", "游戏转出"); // 转账记录用type区分类型

   
    
    public static EJourBizTypeUser2 getBizType(String code) {
        Map<String, EJourBizTypeUser2> map = getBizTypeMap();
        EJourBizTypeUser2 result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeUser2> getBizTypeMap() {
        Map<String, EJourBizTypeUser2> map = new HashMap<String, EJourBizTypeUser2>();
        for (EJourBizTypeUser2 bizType : EJourBizTypeUser2.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeUser2(String code, String value) {
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
