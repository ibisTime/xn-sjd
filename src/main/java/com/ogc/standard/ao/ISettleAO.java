package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Settle;

/**
 * 结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:33:11 PM 
 * @history:
 */
public interface ISettleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void approveSettleByRefCode(String refCode, String refType,
            String approveResult, String handler, String handleNote);

    public Paginable<Settle> querySettlePage(int start, int limit,
            Settle condition);

    public List<Settle> querySettleList(Settle condition);

    public Settle getSettle(String code);

}
