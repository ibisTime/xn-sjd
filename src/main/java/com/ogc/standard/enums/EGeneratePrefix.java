package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    DH("DH", "导航"), JS("JS", "角色"), ADDRESS("AD", "地址"), CD("CD", "菜单"), GS(
            "GS", "公司"), XX("XX", "信息"), RZ("RZ", "日志"), TRADE_ORDER("JY",
            "交易订单"),

    Account("A", "账户"), AJour("AJ", "账户流水"), Charge("C", "充值"),

    Interact("I", "点赞"), BANK_CARD("BC", "银行卡"),

    APPLY_BIND_MAINTAIN("ABM", "申请绑定养护方"), ADOPT_ORDER("AO", "个人/定向/捐赠认养订单"), GROUP_ADOPT_ORDER(
            "GAO", "集体认养订单"), ADOPT_ORDER_TREE("AOT", "认养权"), GIVE_TREE_RECORD(
            "GTR", "赠送树记录"), VISITOR("V", "来访人"), GIFT_ORDER("GO", "礼物订单"), ARTICLE(
            "AR", "文章"), CARBON_BUBBLE_ORDER("CBO", "碳泡泡产生订单"),

    Category("C", "产品类型"), Product("P", "认养产品"), ProductSpec("PS", "产品规格"), Tree(
            "T", "古树"), Maintainer("M", "养护人"), MaintainProject("MP", "养护项目"), MaintainRecord(
            "MR", "养护记录"),

    Settle("S", "结算订单"), GiveCarbonBubbleRecord("GCBR", "赠送碳泡泡记录"), ShareRecord(
            "SR", "分享记录"), AgentUser("AG", "代理人"),

    TOOL_ORDER("TO", "道具购买订单"), TOOL_USE_RECORD("TR", "道具使用记录"),

    Commodity("CO", "商品"), cart("CA", "购物车"), CommodityOrder("COO", "商品订单（多店铺）"), CommodityOrderDetail(
            "COOD", "商品订单（单店铺）"), PostageTemplate("PO", "邮费模版"), Comment("CO",
            "评论"), AfterSale("AS", "售后"), Session("SE", "会话"),

    PRESELL_PRODUCT("PP", "预售产品"), PRESELL_SPECS("PS", "预售规格"), PRESELL_ORDER(
            "PS", "预售订单"),

    ORIGINAL_GROUP("OG", "预售原生组"), PRESELL_INVENTORY("PI", "预售权"), DERIVE_GROUP(
            "DG", "预售派生组"),

    PRESELL_LOGISTICS("PL", "预售物流单"), GROUP_ORDER("GO", "寄售订单"),
    
    COMMODITY_SPECS("CS","商品规格"),DEFAULT_POSTAGE("DP","默认邮费"),SEARCH_HISTORY("SH","搜索历史"),
    
    STEAL_CARBON_BUBBLE_RECORD("SCBR","偷取碳泡泡记录"),
    
    NOTIFY_USER("NU","审核通知人");

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
