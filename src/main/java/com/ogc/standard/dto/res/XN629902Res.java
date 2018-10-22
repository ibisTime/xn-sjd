package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/**
 * 代理商佣金
 * @author: silver 
 * @since: Oct 22, 2018 4:23:33 PM 
 * @history:
 */
public class XN629902Res {
    // 金额
    private BigDecimal totalAmount;

    public XN629902Res(BigDecimal totalAmount) {
        super();
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
