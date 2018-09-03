package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Handicap;
import com.ogc.standard.dto.res.XN650065Res;

public interface IHandicapAO {
    static final String DEFAULT_ORDER_COLUMN = "id";

    public Paginable<Handicap> queryHandicapPage(int start, int limit,
            Handicap condition);

    public List<Handicap> queryHandicapList(Handicap condition);

    public XN650065Res getHandicap(String symbol, String toSymbol);

}
