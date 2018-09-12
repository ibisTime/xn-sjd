package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;

public interface ICoinAcceptOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String buyOrder(XN625270Req req);

    public String sellOrder(XN625271Req req);

    public void cancelBuyOrder(String code, String userId, String remark);

    // 标记打款
    public void markPay(String code, String note, String pdf, String updater);

    // 平台收款处理
    public TradeOrder release(String code, String result, String updater);

    public Paginable<CoinAcceptOrder> queryCoinAcceptOrderPage(int start,
            int limit, CoinAcceptOrder condition);

    public List<CoinAcceptOrder> queryCoinAcceptOrderList(
            CoinAcceptOrder condition);

    public CoinAcceptOrder getCoinAcceptOrder(String code);

}
