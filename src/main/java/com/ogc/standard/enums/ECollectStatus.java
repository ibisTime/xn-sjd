package com.ogc.standard.enums;

public enum ECollectStatus {
    // Broadcast("0", "广播中"), Broadcast_YES("1", "广播成功"), Broadcast_NO("2",
    // "广播失败");
    TO_COLLECT("0", "等待归集")

    , FEE_BROADCAST("1", "获取矿工费广播中")

    , BROADCAST("2", "归集广播中")

    , COLLECT_YES("3", "归集成功")

    , COLLECT_NO("4", "归集失败");
    ECollectStatus(String code, String value) {
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
