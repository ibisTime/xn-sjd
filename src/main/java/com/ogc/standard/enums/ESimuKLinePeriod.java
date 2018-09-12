package com.ogc.standard.enums;

/**
 * 
 * @author: lei 
 * @since: 2018年8月31日 上午1:15:28 
 * @history:
 */
public enum ESimuKLinePeriod {

    MIN1("1min", "1分钟"), MIN5("5min", "5分钟"), MIN15("15min",
            "15分钟"), MIN30("30min", "30分钟");

    ESimuKLinePeriod(String code, String value) {
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
