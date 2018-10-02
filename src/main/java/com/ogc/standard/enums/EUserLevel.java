package com.ogc.standard.enums;

/**
 * 用户等级
 * @author: xieyj
 * @since: 2016年5月24日 上午9:11:47
 * @history:
 */
public enum EUserLevel {
    ZERO("0", "初探翠林"), ONE("1", "护树新秀"), TWO("2", "护树高手"), THREE("3", "育树林丰"), FOUR(
            "4", "愈林诗人"), FIVE("5", "爱林天使");

    EUserLevel(String code, String value) {
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
