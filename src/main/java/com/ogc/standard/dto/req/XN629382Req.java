package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上架弹幕
 * @author: silver 
 * @since: Nov 23, 2018 3:16:05 PM 
 * @history:
 */
public class XN629382Req {

    // 编号
    @NotBlank
    private String code;

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
