package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CoinAcceptOrder;



public interface ICoinAcceptOrderAO {
	static final String DEFAULT_ORDER_COLUMN = "code";


	public String addCoinAcceptOrder(CoinAcceptOrder data);

	public int dropCoinAcceptOrder(String code);

	public int editCoinAcceptOrder(CoinAcceptOrder data);

	public Paginable<CoinAcceptOrder> queryCoinAcceptOrderPage(int start, int limit, CoinAcceptOrder condition);

	public List<CoinAcceptOrder> queryCoinAcceptOrderList(CoinAcceptOrder condition);

	public CoinAcceptOrder getCoinAcceptOrder(String code);

}