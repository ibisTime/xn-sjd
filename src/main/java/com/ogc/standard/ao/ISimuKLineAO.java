package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.SimuKLine;

public interface ISimuKLineAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public List<SimuKLine> querySimuKLineList(String symbol, String toSymbol,
            String period);
}
