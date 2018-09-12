package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 撮合结果历史
* @author: lei
* @since: 2018年09月02日 下午03:13:42
* @history:
*/
public class SimuMatchResultHistory extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 撮合编号
    private Long id;

    // 委托单买单编号
    private String buyOrderCode;

    // 委托单卖单编号
    private String sellOrderCode;

    // 交易币种
    private String symbol;

    // 计价币种
    private String toSymbol;

    // 买方用户编号
    private String buyUserId;

    // 卖方用户编号
    private String sellUserId;

    // 交易数量
    private BigDecimal buyAmount;

    // 计价数量
    private BigDecimal sellAmount;

    // 手续费
    private BigDecimal fee;

    // 撮合时间
    private Date createDatetime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBuyOrderCode(String buyOrderCode) {
        this.buyOrderCode = buyOrderCode;
    }

    public String getBuyOrderCode() {
        return buyOrderCode;
    }

    public void setSellOrderCode(String sellOrderCode) {
        this.sellOrderCode = sellOrderCode;
    }

    public String getSellOrderCode() {
        return sellOrderCode;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setToSymbol(String toSymbol) {
        this.toSymbol = toSymbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

    public void setBuyUserId(String buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getBuyUserId() {
        return buyUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

}
