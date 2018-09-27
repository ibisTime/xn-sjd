package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 下单认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:38:41 
 * @history:
 */
public class XN629030Req {

    // 订单类型（1个人/2定向/3捐赠）
    @NotBlank
    private String type;

    // 认养产品编号
    @NotBlank
    private String productCode;

    // 产品规格编号
    @NotBlank
    private String specsCode;

    // 下单人编号
    @NotBlank
    private String userId;

    // 数量
    @NotBlank
    private String quantity;

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

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
