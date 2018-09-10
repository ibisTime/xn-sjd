package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CoinAcceptOrder;



public interface ICoinAcceptOrderBO extends IPaginableBO<CoinAcceptOrder> {


	public boolean isCoinAcceptOrderExist(String code);


	public String saveCoinAcceptOrder(CoinAcceptOrder data);


	public int removeCoinAcceptOrder(String code);


	public int refreshCoinAcceptOrder(CoinAcceptOrder data);


	public List<CoinAcceptOrder> queryCoinAcceptOrderList(CoinAcceptOrder condition);


	public CoinAcceptOrder getCoinAcceptOrder(String code);


}