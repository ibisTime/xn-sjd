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

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 委托单编号
    private String orderCode;

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

}
