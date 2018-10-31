package com.ogc.standard.enums;

/**
 * 认养权状态
 * @author: jiafr 
 * @since: 2018年9月28日 下午1:46:58 
 * @history:
 */
public enum EAdoptOrderTreeStatus {
    TO_ADOPT("1", "待认养"), ADOPT("2", "认养中"), END("3", "已到期"), PRESENT("4",
            "已赠送"), INVALID("5", "已失效");

    EAdoptOrderTreeStatus(String code, String value) {
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
