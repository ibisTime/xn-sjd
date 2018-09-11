package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.req.XN625271Req;

public interface ICoinAcceptOrderBO extends IPaginableBO<CoinAcceptOrder> {

    public boolean isCoinAcceptOrderExist(String code);

    public String saveBuyAcceptOrder(XN625270Req req, String acceptUser);

    public String saveSellAcceptOrder(XN625271Req req, String acceptUser);

    public int removeCoinAcceptOrder(String code);

    public int cancel(CoinAcceptOrder acceptOrder, String updater,
            String remark);

    public int markPay(CoinAcceptOrder tradeOrder, String updater,
            String remark);

    public int refreshCoinAcceptOrder(CoinAcceptOrder data);

    public List<CoinAcceptOrder> queryCoinAcceptOrderList(
            CoinAcceptOrder condition);

    public CoinAcceptOrder getCoinAcceptOrder(String code);

}
