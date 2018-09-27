package com.ogc.standard.dto.req;

/**
 * 列表查询个人捐赠定向认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:03:26 
 * @history:
 */
public class XN629047Req extends AListReq {

    private static final long serialVersionUID = 4706372426505590195L;

    // 订单类型（1个人/2定向/3捐赠）
    private String type;

    // 认养产品编号
    private String productCode;

    // 规格名称
    private String productSpecsName;

    // 状态(0待支付1已取消2待认养3认养中4已到期)
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
