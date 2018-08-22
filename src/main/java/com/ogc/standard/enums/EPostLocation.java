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
 * 帖子位置（(0普通 1热门)）
 * @author: silver 
 * @since: 2018年8月22日 下午3:46:47 
 * @history:
 */
public enum EPostLocation {
    HOT("1", "热门"), NORMAL("0", "普通");

    EPostLocation(String code, String value) {
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
