package com.ogc.standard.market;

import java.math.BigDecimal;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:10:35 
 * @history:
 */
public class MarketDepthItem {

    // 价格
    private BigDecimal price;

    // 数量
    private BigDecimal amount;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MarketDepthItem [price=" + price + ", amount=" + amount + "]";
    }

}
