/**
 * @Title ECheckResult.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:58:54 
 * @version V1.0   
 */
package com.ogc.standard.enums;

/**
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午7:53:51 
 * @history:
 */
public enum ESimuOrderType {

    MARKET("0", "市价"), LIMIT("1", "限价");

    ESimuOrderType(String code, String value) {
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
