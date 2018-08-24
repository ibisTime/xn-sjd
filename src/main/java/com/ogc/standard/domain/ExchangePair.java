package com.ogc.standard.domain;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * 交易对
 * @author: lei 
 * @since: 2018年8月22日 下午4:17:39 
 * @history:
 */
public class ExchangePair extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // 交易所英文名
    private String exchangeEname;

    // 基础币种
    private String symbol;

    // 计价币种
    private String toSymbol;

    // 交易对
    private BigDecimal price;

    // *******db properties*******
    // 关键字
    private String keywords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExchangeEname() {
        return exchangeEname;
    }

    public void setExchangeEname(String exchangeEname) {
        this.exchangeEname = exchangeEname;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
