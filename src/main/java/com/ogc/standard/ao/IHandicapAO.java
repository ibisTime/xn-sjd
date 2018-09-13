package com.ogc.standard.ao;

import com.ogc.standard.dto.res.XN650065Res;

public interface IHandicapAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public XN650065Res getHandicap(String symbol, String toSymbol);

}
