package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:25:17 
 * @history:
 */
public class MarketHuobiproLog extends ABaseDO {

    private static final long serialVersionUID = 4581560727686616236L;

    // id
    private String id;

    // 基础币种
    private String symbol;

    // 计价币种
    private String toSymbol;

    // 交易对
    private String symbolPair;

    // K线id
    private String kId;

    // 成交量
    private BigDecimal amount;

    // 成交笔数
    private BigDecimal count;

    // 开盘价
    private BigDecimal open;

    // 收盘价,当K线为最晚的一根时，是最新成交价
    private BigDecimal close;

    // 最低价
    private BigDecimal low;

    // 最高价
    private BigDecimal high;

    // 成交额, 即 sum(每一笔成交价 * 该笔的成交量)
    private BigDecimal vol;

    // 买1价
    private BigDecimal bidPrice;

    // 买1量
    private BigDecimal bidAmount;

    // 卖1价
    private BigDecimal askPrice;

    // 卖1量
    private BigDecimal askAmount;

    // 响应生成时间点，单位：毫秒
    private String ts;

    // 更新时间
    private Date createDatetime;

    // *************db properties*************

    private long periodTs;// 1天0点，7天0点，30天0点

    public long getPeriodTs() {
        return periodTs;
    }

    public void setPeriodTs(long periodTs) {
        this.periodTs = periodTs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSymbolPair() {
        return symbolPair;
    }

    public void setSymbolPair(String symbolPair) {
        this.symbolPair = symbolPair;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public BigDecimal getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(BigDecimal askPrice) {
        this.askPrice = askPrice;
    }

    public BigDecimal getAskAmount() {
        return askAmount;
    }

    public void setAskAmount(BigDecimal askAmount) {
        this.askAmount = askAmount;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Override
    public String toString() {
        return "MarketHuobiproLog [id=" + id + ", symbol=" + symbol
                + ", toSymbol=" + toSymbol + ", symbolPair=" + symbolPair
                + ", kId=" + kId + ", amount=" + amount + ", count=" + count
                + ", open=" + open + ", close=" + close + ", low=" + low
                + ", high=" + high + ", vol=" + vol + ", bidPrice=" + bidPrice
                + ", bidAmount=" + bidAmount + ", askPrice=" + askPrice
                + ", askAmount=" + askAmount + ", ts=" + ts
                + ", createDatetime=" + createDatetime + "]";
    }
}
