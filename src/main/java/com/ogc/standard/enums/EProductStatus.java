package com.ogc.standard.enums;

/**
 * 产品状态
 * @author: silver 
 * @since: 2018年9月26日 下午8:15:22 
 * @history:
 */
public enum EProductStatus {

    DRAFT("0", "草稿"), TO_APPROVE("1", "已提交待审核"), APPROVE_NO("2",
            "审核不通过"), TO_PUTON("3", "审核通过待上架"), TO_ADOPT("4", "已上架待认养"), LOCKED(
                    "5", "已锁定"), TO_PUTOFF("6", "已认养可下架"), PUTOFFED("7", "已下架");

    EProductStatus(String code, String value) {
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
