package com.ogc.standard.enums;

/**
 * 申请绑定养护方记录状态
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:15:23 
 * @history:
 */
public enum EApplyBindMaintainStatus {

    TO_APPROVE("1", "待审核"), NO_PASS("2", "审核不通过"), BIND("3", "已绑定"), UNBIND(
            "4", "已解除");

    EApplyBindMaintainStatus(String code, String value) {
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
