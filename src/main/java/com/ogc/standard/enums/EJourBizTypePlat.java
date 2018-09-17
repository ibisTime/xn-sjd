package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypePlat {

    AJ_DEPOSIT("deposit", "定存"), 
    AJ_COLLECT("collect", "归集"), 
    AJ_WITHDRAW("withdraw", "提币"),

    AJ_WITHDRAW_FEE("withdraw_fee", "提币手续费"), 
    AJ_CCORDER_FEE("ccorder_fee", "场外cc手续费"), 
    AJ_BBORDER_FEE("bborder_fee", "币币交易手续费"), 
    AJ_WITHDRAW_MINING_FEE("withdraw_mining_fee", "提币矿工费"), 
    AJ_COLLECT_FIRST_MINING_FEE("collect_first_mining_fee", "一级归集矿工费"), 
    AJ_DEPOSIT_MINING_FEE("deposit_mining_fee", "定存矿工费"),

    AJ_WITHDRAW_SUPPLY_MINING_FEE_ERC20("withdraw_supply_mining_fee_erc20", "ERC20币的矿工费补给"), 
    AJ_WITHDRAW_MINING_FEE_ERC20("withdraw_mining_fee_erc20", "ERC20币提币矿工费"), 
    AJ_COLLECT_MINING_FEE_ERC20("collect_first_mining_fee_erc20", "ERC20币一级归集矿工费"), 
    AJ_DEPOSIT_MINING_FEE_ERC20("deposit_mining_fee_erc20", "ERC20币定存矿工费");

    public static EJourBizTypePlat getBizType(String code) {
        Map<String, EJourBizTypePlat> map = getBizTypeMap();
        EJourBizTypePlat result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypePlat> getBizTypeMap() {
        Map<String, EJourBizTypePlat> map = new HashMap<String, EJourBizTypePlat>();
        for (EJourBizTypePlat bizType : EJourBizTypePlat.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypePlat(String code, String value) {
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
