package com.ogc.standard.enums;

/**
 * 代理用户状态
 * @author: silver 
 * @since: 2018年9月28日 下午2:08:17 
 * @history:
 */
public enum EAgentUserStatus {
    TO_FILL("-1", "待填写资料"), TO_APPROVE("0", "待审核"), APPROVE_NO("1", "审核不通过"), NORMAL(
            "2", "正常"), CANCEL("3", "已注销");

    EAgentUserStatus(String code, String value) {
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
