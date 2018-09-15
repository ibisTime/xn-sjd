package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Divide;

public interface IDivideAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void addDivide();

    public Paginable<Divide> queryDividePage(int start, int limit,
            Divide condition);

    public List<Divide> queryDivideList(Divide condition);

    // public Divide getDivide(String code);

}
