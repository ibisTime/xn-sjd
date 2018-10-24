package com.ogc.standard.enums;

/**
 * 古树状态
 * @author: silver 
 * @since: 2018年9月26日 下午1:53:37 
 * @history:
 */
public enum ETreeStatus {

    TO_ADOPT("0", "待认养"), ADOPTED("2", "已认养");

    ETreeStatus(String code, String value) {
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
