package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DivideDetail;

public interface IDivideDetailAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<DivideDetail> queryDivideDetailPage(int start, int limit,
            DivideDetail condition);

    public List<DivideDetail> queryDivideDetailList(DivideDetail condition);

    public DivideDetail getDivideDetail(String code);

}
