package com.ogc.standard.dto.req;

/**
 * 分页查询寄售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:31:02 PM 
 * @history:
 */
public class XN629475Req extends APageReq {

    private static final long serialVersionUID = 129338806402344599L;

    // 产品编号
    private String productCode;

    // 下单人编号
    private String userId;

    // 状态
    private String status;

    // 预售类型
    private String presellType;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getPresellType() {
        return presellType;
    }

    public void setPresellType(String presellType) {
        this.presellType = presellType;
    }

}
