package com.ogc.standard.enums;

/**
 * 代理用户类型
 * @author: silver 
 * @since: 2018年9月28日 下午2:08:17 
 * @history:
 */
public enum EAgentUserKind {
    Agent("0", "代理商"), Salesman("1", "业务员");

    EAgentUserKind(String code, String value) {
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
