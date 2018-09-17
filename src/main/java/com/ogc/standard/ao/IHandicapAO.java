package com.ogc.standard.ao;

import com.ogc.standard.dto.res.XN650065Res;
import com.ogc.standard.market.MarketDepth;

public interface IHandicapAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public XN650065Res getHandicap(String symbol, String toSymbol);

    public MarketDepth getMarketDepth(String symbol, String toSymbol);

}
