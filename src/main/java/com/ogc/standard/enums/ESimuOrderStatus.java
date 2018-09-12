package com.ogc.standard.enums;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午2:57:11 
 * @history:
 */
public enum ESimuOrderStatus {

    SUBMIT("0", "待成交"), PART_DEAL("1", "部分成交"), PART_DEAL_CANCEL("2",
            "部分成交撤销"), ENTIRE_DEAL("3", "完全成交"), CANCEL("4", "已撤销");

    ESimuOrderStatus(String code, String value) {
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
