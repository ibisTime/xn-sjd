package com.ogc.standard.domain;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 预售规格
* @author: silvers 
* @since: 2018-11-02 16:52:45
* @history:
*/
public class PresellSpecs extends ABaseDO {

    private static final long serialVersionUID = -5389360703293861239L;

    // 编号
    private String code;

    // 产品编号
    private String productCode;

    // 规格名称
    private String name;

    // 包装重量
    private Integer packCount;

    // 价格
    private BigDecimal price;

    // 每小时涨幅
    private Double increase;

    /*****************DB Properties*******************/
    // 最小涨幅
    private Double minIncrease;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPackCount() {
        return packCount;
    }

    public void setPackCount(Integer packCount) {
        this.packCount = packCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getIncrease() {
        return increase;
    }

    public void setIncrease(Double increase) {
        this.increase = increase;
    }

    public Double getMinIncrease() {
        return minIncrease;
    }

    public void setMinIncrease(Double minIncrease) {
        this.minIncrease = minIncrease;
    }

}
