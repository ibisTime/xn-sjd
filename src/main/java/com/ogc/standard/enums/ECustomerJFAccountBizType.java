package com.ogc.standard.enums;

/**
 * 用户积分账户总金额流水bizType
 * @author: jiafr 
 * @since: 2018年9月29日 下午10:19:51 
 * @history:
 */
public enum ECustomerJFAccountBizType {

    PERSON("0", "个人"), DIRECT("1", "定向"), DONATE("2", "捐赠"), COLLECTIVE("3",
            "集体");

    ECustomerJFAccountBizType(String code, String value) {
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
