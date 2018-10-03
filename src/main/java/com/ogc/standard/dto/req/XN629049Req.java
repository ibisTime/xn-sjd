package com.ogc.standard.dto.req;


public class XN629049Req extends APageReq {

    private static final long serialVersionUID = 6541979603349552583L;

    // 订单类型（1个人/2定向/3捐赠）
    private String type;

    // 用户编号
    private String userId;

    // 状态(0待支付1已取消2待认养3认养中4已到期)
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
