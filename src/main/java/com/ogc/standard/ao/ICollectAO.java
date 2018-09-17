package com.ogc.standard.ao;

import java.math.BigDecimal;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Collect;

public interface ICollectAO {

    // static final String DEFAULT_ORDER_COLUMN = "code";
    //
    public Paginable<Collect> queryCollectPage(int start, int limit,
            Collect condition);

    public Collect getCollect(String code);

    // public BigDecimal getTotalCollect(String currency);
    //
    // // 手动归集
    // public void collectionManual(BigDecimal balanceStart, String currency);

    // btc,eth,wan归集
    public void collect(BigDecimal balanceStart, String currency, String refNo);

    // eth token的手动归集
    public void ethTokenCollect(String balanceStart, String currency,
            String refNo);

    // eth token的自动归集
    public void ethTokenCollectAuto(String address, String symbol, String refNo);
}
