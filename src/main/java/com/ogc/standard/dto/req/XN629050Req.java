package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 第一人下单集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:37:44 
 * @history:
 */
public class XN629050Req {

    // 认养产品编号
    @NotBlank
    private String productCode;

    // 产品规格编号
    @NotBlank
    private String specsCode;

    // 下单人编号
    @NotBlank
    private String userId;

    // 下单数量
    @NotBlank
    private String quantity;

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
