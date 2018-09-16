package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AcceptOrder;
import com.ogc.standard.domain.Bankcard;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;

public interface IAcceptOrderBO extends IPaginableBO<AcceptOrder> {

    public String saveBuyAcceptOrder(XN625270Req req,
            List<Bankcard> bankcardList);

    public AcceptOrder saveSellAcceptOrder(XN625271Req req);

    public int cancel(AcceptOrder acceptOrder, String updater, String remark);

    public int markPay(AcceptOrder tradeOrder, String updater);

    public int platMarkPay(AcceptOrder tradeOrder, String updater, String note);

    public List<AcceptOrder> queryCoinAcceptOrderList(AcceptOrder condition);

    public AcceptOrder getAcceptOrder(String code);

    public void check(String userId, String tradePrice, String tradeCurrecny,
            String tradeCount, String amount);

}
