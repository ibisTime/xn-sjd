package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置权重(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午4:59:26 
 * @history:
 */
public class XN628011Req {
    // 战队编号
    @NotBlank
    private String code;

    // 位置(0=普通 1=热门)
    @NotBlank
    private String location;

    // 序号
    @NotBlank
    private String orderNo;

    // 更新人
    @NotBlank
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
