package com.ogc.standard.ao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Collection;

public interface ICollectionAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Collection> queryCollectionPage(int start, int limit,
            Collection condition);

    public Collection getCollection(String code);

    public BigDecimal getTotalCollect(String currency);

    // // 手动归集
    // public void collectionManual(BigDecimal balanceStart, String currency);

    // 归集
    public void collect(BigDecimal balanceStart, String currency, String refNo);

}
