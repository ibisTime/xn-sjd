/**
 * @Title Cart.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:19:16 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: taojian 
 * @since: 2018年11月5日 下午9:19:16 
 * @history:
 */
public class Cart extends ABaseDO {

    private static final long serialVersionUID = 6449258546803422077L;

    // *******DB********
    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 商品编号
    private String commodityCode;

    // 商品名称
    private String commodityName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 数量
    private Long quantity;

    // *******DB********

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
