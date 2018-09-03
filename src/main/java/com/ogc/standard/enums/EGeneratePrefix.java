package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 上午8:29:02 
 * @history:
 */
public enum EGeneratePrefix {

    DH("DH", "导航"), JS("JS", "角色"), CD("CD", "菜单"), BM("BM", "部门"), GS("GS",
            "公司"), XX("XX", "信息"), RZ("RZ", "日志"), SIMU_ORDER("SO", "币币交易委托单"),

    SIMU_ORDER_DETAIL("SOMX", "币币交易委托单明细"), SIMU_ORDER_HISTORY("SOT",
            "币币交易历史委托单"), Account("A", "账户"), AJour("AJ", "账户流水"),

    Match("M", "赛事"), MatchApply("MA", "参赛申请"), Team("T",
            "战队"), TeamMemberApply("TMA", "战队成员申请"), GROUP("GP",
                    "组合"), ATTENTION("AT", "关注/提醒"), Post("P",
                            "帖子"), Comment("C", "评论"), Interact("I", "点赞");

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
