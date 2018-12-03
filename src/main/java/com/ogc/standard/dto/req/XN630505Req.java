package com.ogc.standard.dto.req;

public class XN630505Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 店铺编号
    private String shopCode;

    // 名字（选填）
    private String name;

    // 类型（选填）
    private String type;

    // 状态（选填）
    private String status;

    // 位置（选填）
    private String location;

    // 父编号（选填）
    private String parentCode;

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

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
