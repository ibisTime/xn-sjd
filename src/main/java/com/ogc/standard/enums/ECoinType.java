/**
 * @Title ECoinType.java 
 * @Package com.ogc.standard.enums 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:30:16 
 * @version V1.0   
 */
package com.ogc.standard.enums;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:30:16 
 * @history:
 */
public enum ECoinType {

    // 公链币
    ETH("0", "以太币"), BTC("1", "比特币")

    // 基于某条公链的token币
    , X("0T", "以太token币");

    ECoinType(String code, String value) {
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
