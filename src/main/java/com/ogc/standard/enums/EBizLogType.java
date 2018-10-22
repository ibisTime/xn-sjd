package com.ogc.standard.enums;

/**
 * 日志类型
 * @author: jiafr 
 * @since: 2018年9月28日 下午3:14:22 
 * @history:
 */
public enum EBizLogType {

    GIVE("1", "赠送"), LEAVE_MESSAGE("2", "留言"), GATHER("3",
            "收取碳泡泡"), GATHER_NO("4", "收取碳泡泡被阻挡");

    EBizLogType(String code, String value) {
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
