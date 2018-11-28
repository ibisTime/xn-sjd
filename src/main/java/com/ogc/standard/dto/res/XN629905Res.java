package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/**
 * 养护产权收益
 * @author: silver 
 * @since: 2018年8月24日 上午10:31:15 
 * @history:
 */
public class XN629905Res {
    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public XN629905Res(BigDecimal totalAmount) {
        super();
        this.totalAmount = totalAmount;
    }

}
