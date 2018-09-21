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
 * 英文文本
 * @author: taojian 
 * @since: 2018年9月20日 下午7:43:58 
 * @history:
 */
public enum ELanguage_en_US {
    // 1
    MOBILEEXIST("000001", "Mobile is exist");

    ELanguage_en_US(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static Map<String, ELanguage_en_US> getMap() {
        Map<String, ELanguage_en_US> map = new HashMap<String, ELanguage_en_US>();
        for (ELanguage_en_US eLanguage_en_US : ELanguage_en_US.values()) {
            map.put(eLanguage_en_US.getCode(), eLanguage_en_US);
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
