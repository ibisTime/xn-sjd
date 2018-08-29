package com.ogc.standard.huobipro;

/**
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:23:08 
 * @history:
 */
public class HBSymbol {

    // 基础币种
    private String baseCurrency;

    // 计价币种
    private String quoteCurrency;

    // 价格精度位数（0为个位）
    private String pricePrecision;

    // 数量精度位数（0为个位）
    private String amountPrecision;

    // 交易区
    private String symbolPartition;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    public String getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(String pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public String getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(String amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public String getSymbolPartition() {
        return symbolPartition;
    }

    public void setSymbolPartition(String symbolPartition) {
        this.symbolPartition = symbolPartition;
    }

}
