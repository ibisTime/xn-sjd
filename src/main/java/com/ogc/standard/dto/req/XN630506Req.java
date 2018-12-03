package com.ogc.standard.dto.req;

public class XN630506Req {

    // 店铺编号
    private String shopCode;

    // 类型（选填）
    private String type;

    // UI位置（选填）
    private String location;

    // 父编号（选填）
    private String parentCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
