/**
 * @Title XN629710Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:08:41 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午11:08:41 
 * @history:
 */
public class XN629710Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 商品编号
    @NotBlank
    private String commodityCode;

    // 商品名称
    @NotBlank
    private String commodityName;

    // 规格编号
    @NotBlank
    private String specsCode;

    // 规格名称
    @NotBlank
    private String specsName;

    // 数量
    @NotBlank
    private String quantity;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
