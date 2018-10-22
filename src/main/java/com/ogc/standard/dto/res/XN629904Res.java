package com.ogc.standard.dto.res;

import java.math.BigDecimal;

/**
 * 产权方已认养的总值
 * @author: silver 
 * @since: Oct 22, 2018 3:56:36 PM 
 * @history:
 */
public class XN629904Res {
    // 金额
    private BigDecimal totalAmount;

    // 树木数量
    private long treeCount;

    public XN629904Res(BigDecimal totalAmount, long treeCount) {
        super();
        this.totalAmount = totalAmount;
        this.treeCount = treeCount;
    }

    public long getTreeCount() {
        return treeCount;
    }

    public void setTreeCount(long treeCount) {
        this.treeCount = treeCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
