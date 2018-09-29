package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Settle;

/**
 * 结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:33:11 PM 
 * @history:
 */
@Component
public interface ISettleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public void approveSettleByRef(String refCode, String approveResult,
            String handleNote);

    public Paginable<Settle> querySettlePage(int start, int limit,
            Settle condition);

    public List<Settle> querySettleList(Settle condition);

    public Settle getSettle(String code);

}
