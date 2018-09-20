/**
 * @Title Status.java 
 * @Package com.ibis.pz.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午8:41:50 
 * @version V1.0   
 */
package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * 中文文本
 * @author: taojian 
 * @since: 2018年9月20日 下午7:44:25 
 * @history:
 */
public enum ELanguage_zh_CN {
    // 1
    MOBILEEXIST("000001", "手机号已存在");

    ELanguage_zh_CN(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ELanguage_zh_CN> getMap() {
        Map<String, ELanguage_zh_CN> map = new HashMap<String, ELanguage_zh_CN>();
        for (ELanguage_zh_CN eLanguage_zh_CN : ELanguage_zh_CN.values()) {
            map.put(eLanguage_zh_CN.getCode(), eLanguage_zh_CN);
        }
        return map;
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
