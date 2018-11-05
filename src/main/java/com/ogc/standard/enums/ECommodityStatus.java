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
 * 商品状态
 * @author: taojian 
 * @since: 2018年11月5日 上午10:23:56 
 * @history:
 */
public enum ECommodityStatus {

    DRAFT("0", "草稿"), TOAPPROVE("1", "待审核"), FAILED("2", "审核不通过"), PASS("3",
            "审核通过"), ON("4", "已上架"), OFF("5", "已下架");

    ECommodityStatus(String code, String value) {
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
