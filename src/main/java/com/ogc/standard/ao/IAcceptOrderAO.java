package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.AcceptOrder;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;

public interface IAcceptOrderAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public String buyOrder(XN625270Req req);

    public String sellOrder(XN625271Req req);

    public void cancelBuyOrder(String code, String userId, String remark);

    // 标记打款
    public void markPay(String code, String updater);

    // 平台收款处理
    public void release(String code, String result, String updater);

    public void platPay(String code, String result, String updater);

    public Paginable<AcceptOrder> queryAcceptOrderPage(int start, int limit,
            AcceptOrder condition);

    public List<AcceptOrder> queryAcceptOrderList(AcceptOrder condition);

    public AcceptOrder getAcceptOrder(String code);

}
