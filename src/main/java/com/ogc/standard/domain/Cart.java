/**
 * @Title Cart.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月5日 下午9:19:16 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;

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

    // 店铺编号
    private String shopCode;

    // 用户编号
    private String userId;

    // 商品编号
    private String commodityCode;

    // 商品名称
    private String commodityName;

    // 规格编号
    private Long specsId;

    // 规格名称
    private String specsName;

    // 数量
    private Long quantity;

    // 总额
    private BigDecimal amount;

    // *******DB********
    // 店铺名称
    private String shopName;

    // 商品图片
    private String commodityPhoto;

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

    public Long getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Long specsId) {
        this.specsId = specsId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getCommodityPhoto() {
        return commodityPhoto;
    }

    public void setCommodityPhoto(String commodityPhoto) {
        this.commodityPhoto = commodityPhoto;
    }

}
