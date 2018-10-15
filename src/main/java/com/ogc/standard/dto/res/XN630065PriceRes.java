package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/**
 * 产权方古树市值
 * @author: silver 
 * @since: Oct 15, 2018 8:17:06 PM 
 * @history:
 */
public class XN630065PriceRes {
    // 最大市值
    private BigDecimal maxPrice;

    // 最小市值
    private BigDecimal minPrice;

    public XN630065PriceRes(BigDecimal maxPrice, BigDecimal minPrice) {
        super();
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

}
