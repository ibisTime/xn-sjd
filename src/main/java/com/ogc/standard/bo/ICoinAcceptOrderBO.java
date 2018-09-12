package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;

public interface ICoinAcceptOrderBO extends IPaginableBO<CoinAcceptOrder> {

    public boolean isCoinAcceptOrderExist(String code);

    public String saveBuyAcceptOrder(XN625270Req req, String receiveCardNo,
            String receiveBank);

    public CoinAcceptOrder saveSellAcceptOrder(XN625271Req req,
            String payCardNo, String payBank);

    public int cancel(CoinAcceptOrder acceptOrder, String updater,
            String remark);

    public int markPay(CoinAcceptOrder tradeOrder, String updater,
            String remark);

    public List<CoinAcceptOrder> queryCoinAcceptOrderList(
            CoinAcceptOrder condition);

    public CoinAcceptOrder getCoinAcceptOrder(String code);

}
