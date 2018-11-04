package com.ogc.standard.dto.req;

/**
 * 列表查询资产
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629437Req extends AListReq {

    private static final long serialVersionUID = 129338806402344599L;

    // 订单/父级原生组编号
    private String orderCode;

    // 产品编号
    private String productCode;

    // 归属人
    private String ownerId;

    // 状态
    private String status;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
