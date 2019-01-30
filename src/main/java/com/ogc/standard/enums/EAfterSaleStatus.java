package com.ogc.standard.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum EAfterSaleStatus {

    TOHANDLE("0", "待处理"),

    PASS("1", "处理通过"),

    FALSE("2", "处理不通过"),

    FINISH("3", "完成"),

    CANCLED("4", "用户取消");

    EAfterSaleStatus(String code, String value) {
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
