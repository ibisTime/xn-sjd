package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Divide;

public interface IDivideAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public Paginable<Divide> queryDividePage(int start, int limit,
            Divide condition);

    public void doDivide(String divideId, String divideProfit,
            String divideUser, String remark);

    public void divide();

}
