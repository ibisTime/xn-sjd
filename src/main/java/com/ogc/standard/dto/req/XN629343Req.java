package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 上架文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:11:07 
 * @history:
 */
public class XN629343Req {

    // 编号
    @NotBlank
    private String code;

    // 操作人
    @NotBlank
    private String updater;

    // UI位置
    @NotBlank
    private String location;

    // UI次序
    @NotBlank
    private String orderNo;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
