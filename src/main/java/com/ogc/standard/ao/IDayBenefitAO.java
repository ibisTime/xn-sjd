package com.ogc.standard.ao;

import com.ogc.standard.dto.res.XN650090Res;

public interface IDayBenefitAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public XN650090Res queryDayBenefitList(String groupCode, String days);

}
