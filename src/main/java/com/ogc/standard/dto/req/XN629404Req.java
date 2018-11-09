package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上架预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:58 AM 
 * @history:
 */
public class XN629404Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    @NotBlank
    private String code;

    // 位置
    private String location;

    // 序号
    @NotBlank
    private String orderNo;

    // 更新人
    private String updater;

    public String getCode() {
        return code;
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

    public void setCode(String code) {
        this.code = code;
    }

}
