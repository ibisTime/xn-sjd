package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630500Req {

    // 店铺编号
    private String shopCode;

    // 名字（必填）
    @NotBlank
    private String name;

    // 类型（必填）
    @NotBlank
    private String type;

    // 访问Url（必填）
    private String url;

    // 图片（选填）
    private String pic;

    // 状态（必填）
    @NotBlank
    private String status;

    // 位置（选填）
    private String location;

    // 相对位置编号（必填）
    @NotBlank
    private String orderNo;

    // 父编号（选填）
    private String parentCode;

    // 备注(选填)
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
