package com.ogc.standard.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

public class CurrencyRate extends ABaseDO implements Serializable {

    private static final long serialVersionUID = 5720433189859876353L;

    // ID主键
    private Integer id;

    // 法币币种
    private String currency;

    // 法币计价币种CNY
    private String referCurrency;

    // 汇率来源
    private String origin;

    // 汇率
    private BigDecimal rate;

    // 更新时间
    private Date updateDatetime;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getReferCurrency() {
        return referCurrency;
    }

    public void setReferCurrency(String referCurrency) {
        this.referCurrency = referCurrency == null ? null
                : referCurrency.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
