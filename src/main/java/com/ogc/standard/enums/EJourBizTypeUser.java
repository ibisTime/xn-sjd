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
public enum EJourBizTypeUser {
    AJ_REG("reg","注册分佣"),

    AJ_CHARGE("charge", "充币"), 
    AJ_WITHDRAW("withdraw", "提币"), 
    AJ_WITHDRAW_FEE("withdraw_fee", "提币手续费"),
    AJ_WITHDRAW_FROZEN("withdraw_frozen", "提币冻结"),
    AJ_WITHDRAW_UNFROZEN("withdraw_unfrozen", "提币解冻"),

    AJ_ACCEPT_BUY("accept_buy", "场外承兑商购买"), 
    AJ_ACCEPT_SELL("accept_sell","场外承兑商出售"), 
    AJ_ACCEPT_FEE("accept_fee", "场外承兑商手续费"),
    AJ_ACCEPT_FROZEN("withdraw_frozen", "场外承兑商冻结"),
    AJ_ACCEPT_UNFROZEN("withdraw_unfrozen", "场外承兑商解冻"),
    
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

   
    
    public static EJourBizTypeUser getBizType(String code) {
        Map<String, EJourBizTypeUser> map = getBizTypeMap();
        EJourBizTypeUser result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeUser> getBizTypeMap() {
        Map<String, EJourBizTypeUser> map = new HashMap<String, EJourBizTypeUser>();
        for (EJourBizTypeUser bizType : EJourBizTypeUser.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeUser(String code, String value) {
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
