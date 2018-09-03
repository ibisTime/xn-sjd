package com.ogc.standard.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

public class Market extends ABaseDO implements Serializable {

    private static final long serialVersionUID = -4119900661343861336L;

    // ID主键
    private Long id;

    // 代币符号
    private String symbol;

    // 参照法币币种
    private String referCurrency;

    // 行情来源
    private String origin;

    // 最新成交价格
    private BigDecimal lastPrice;// Last

    // 买家期望价格
    private BigDecimal bid;// Bid

    // 卖家期望价格
    private BigDecimal ask;// Ask

    // 中间价格
    private BigDecimal mid;// Mid

    // 最低价格
    private BigDecimal low;// Low

    // 最高价格
    private BigDecimal high;// High

    // 交易量
    private String volume;// Volume

    // 更新时间
    private Date updateDatetime;

    // coinmarketcap对一个的ID
    private String CoinmarketcapId;

    private List<String> coinList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getReferCurrency() {
        return referCurrency;
    }

    public void setReferCurrency(String referCurrency) {
        this.referCurrency = referCurrency;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getCoinmarketcapId() {
        return CoinmarketcapId;
    }

    public void setCoinmarketcapId(String coinmarketcapId) {
        CoinmarketcapId = coinmarketcapId;
    }

    public List<String> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<String> coinList) {
        this.coinList = coinList;
    }

}
