/**
 * @Title ESmsType.java 
 * @Package com.ogc.standard.enums 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午2:38:20 
 * @version V1.0   
 */
package com.ogc.standard.enums;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午2:38:20 
 * @history:
 */
public enum ESmsType {

    SYS("0", "系统公告"), APP("1", "APP推送"), MES("2", "短信"), BULLETIN("3", "快报");

    ESmsType(String code, String value) {
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
