package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * 模拟交易委托单明细
 * @author: lei 
 * @since: 2018年8月22日 下午3:45:55 
 * @history:
 */
public class SimuOrderDetail extends ABaseDO {

    private static final long serialVersionUID = -3267238414760698120L;

    // 编号
    private String code;

    // 委托单编号
    private String orderCode;

    // 委托单编号
    private String matchResultId;

    // 用户编号
    private String userId;

    // 交易币种
    private String symbol;

    // 计价币种
    private String toSymbol;

    // 成交价格
    private BigDecimal tradedPrice;

    // 成交数量
    private BigDecimal tradedCount;

    // 成交总金额
    private BigDecimal tradedAmount;

    // 成交手续费
    private BigDecimal tradedFee;

    // 成交时间
    private Date createDatetime;

    // ******************db properties**************

    // 成交时间起
    private Date createDatetimeStart;

    // 成交时间止
    private Date createDatetimeEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMatchResultId() {
        return matchResultId;
    }

    public void setMatchResultId(String matchResultId) {
        this.matchResultId = matchResultId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

    public void setToSymbol(String toSymbol) {
        this.toSymbol = toSymbol;
    }

    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }

    public void setTradedPrice(BigDecimal tradedPrice) {
        this.tradedPrice = tradedPrice;
    }

    public BigDecimal getTradedCount() {
        return tradedCount;
    }

    public void setTradedCount(BigDecimal tradedCount) {
        this.tradedCount = tradedCount;
    }

    public BigDecimal getTradedAmount() {
        return tradedAmount;
    }

    public void setTradedAmount(BigDecimal tradedAmount) {
        this.tradedAmount = tradedAmount;
    }

    public BigDecimal getTradedFee() {
        return tradedFee;
    }

    public void setTradedFee(BigDecimal tradedFee) {
        this.tradedFee = tradedFee;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

}
