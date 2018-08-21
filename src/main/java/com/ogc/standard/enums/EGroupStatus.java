/**
 * @Title EDictType.java 
 * @Package com.std.security.enums 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午5:15:02 
 * @version V1.0   
 */
package com.ogc.standard.enums;

/**
 * 
 * @author: lei 
 * @since: 2018年8月21日 下午5:36:01 
 * @history:
 */
public enum EGroupStatus {
    START("1", "进行中"), END("0", "已结束");

    EGroupStatus(String code, String value) {
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
