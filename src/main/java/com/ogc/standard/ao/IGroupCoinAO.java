package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.GroupCoin;

public interface IGroupCoinAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public List<GroupCoin> queryGroupCoinListByGroupCode(String groupCode);

}
