package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    DH("DH", "导航"), JS("JS", "角色"), CD("CD", "菜单"), GS("GS", "公司"), XX("XX",
            "信息"), RZ("RZ", "日志"), TRADE_ORDER("JY", "交易订单"),

    Account("A", "账户"), SAccount("A", "账户"), AJour("AJ", "账户流水"),

    Interact("I", "点赞"), BANK_CARD("BC", "银行卡"),

    APPLY_BIND_MAINTAIN("ABM", "申请绑定养护方"), ADOPT_ORDER("AO", "个人/定向/捐赠认养订单"), GROUP_ADOPT_ORDER(
            "GAO", "集体认养订单"), ADOPT_ORDER_TREE("AOT", "认养权"), GIVE_TREE_RECORD(
            "GTR", "赠送树记录"),

    Category("C", "产品类型"), Product("P", "认养产品"), ProductSpec("PS", "产品规格"), Tree(
            "T", "古树"), Maintainer("M","养护人"), MaintainProject("MP","养护项目"), MaintainRecord("MR","养护记录"),
    
    Settle("S","结算订单"), GiveCarbonBubbleRecord("GCBR","赠送碳泡泡记录"), ShareRecord("SR","分享记录");

    public static Map<String, EGeneratePrefix> getMap() {
        Map<String, EGeneratePrefix> map = new HashMap<String, EGeneratePrefix>();
        for (EGeneratePrefix orderType : EGeneratePrefix.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EGeneratePrefix(String code, String value) {
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
