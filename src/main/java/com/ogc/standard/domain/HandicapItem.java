package com.ogc.standard.domain;

import java.math.BigDecimal;

/**
 * @author: lei 
 * @since: 2018年8月29日 下午4:03:55 
 * @history:
 */
public class HandicapItem {

    // 价格
    private BigDecimal price;

    // 数量
    private BigDecimal count;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount() {
        return count;
    }

}
