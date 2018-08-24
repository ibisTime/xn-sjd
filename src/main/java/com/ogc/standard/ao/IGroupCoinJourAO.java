package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupCoinJour;

public interface IGroupCoinJourAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<GroupCoinJour> queryGroupCoinJourPage(int start, int limit,
            GroupCoinJour condition);

}
