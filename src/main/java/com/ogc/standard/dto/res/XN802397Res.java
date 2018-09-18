package com.ogc.standard.dto.res;

import java.math.BigDecimal;

public class XN802397Res {

    private BigDecimal platCount;

    private BigDecimal regRefCount;

    private BigDecimal ccTradeCount;

    private BigDecimal bbTradeCount;

    private BigDecimal settleCount;

    private BigDecimal nosettleCount;

    private BigDecimal unsettleCount;

    public BigDecimal getNosettleCount() {
        return nosettleCount;
    }

    public void setNosettleCount(BigDecimal nosettleCount) {
        this.nosettleCount = nosettleCount;
    }

    public BigDecimal getPlatCount() {
        return platCount;
    }

    public void setPlatCount(BigDecimal platCount) {
        this.platCount = platCount;
    }

    public BigDecimal getRegRefCount() {
        return regRefCount;
    }

    public void setRegRefCount(BigDecimal regRefCount) {
        this.regRefCount = regRefCount;
    }

    public BigDecimal getCcTradeCount() {
        return ccTradeCount;
    }

    public void setCcTradeCount(BigDecimal ccTradeCount) {
        this.ccTradeCount = ccTradeCount;
    }

    public BigDecimal getBbTradeCount() {
        return bbTradeCount;
    }

    public void setBbTradeCount(BigDecimal bbTradeCount) {
        this.bbTradeCount = bbTradeCount;
    }

    public BigDecimal getSettleCount() {
        return settleCount;
    }

    public void setSettleCount(BigDecimal settleCount) {
        this.settleCount = settleCount;
    }

    public BigDecimal getUnsettleCount() {
        return unsettleCount;
    }

    public void setUnsettleCount(BigDecimal unsettleCount) {
        this.unsettleCount = unsettleCount;
    }
}
