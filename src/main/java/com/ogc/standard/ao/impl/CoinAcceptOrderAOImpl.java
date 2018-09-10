package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICoinAcceptOrderAO;
import com.ogc.standard.bo.ICoinAcceptOrderBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.exception.BizException;




@Service
public class CoinAcceptOrderAOImpl implements ICoinAcceptOrderAO {

	@Autowired
	private ICoinAcceptOrderBO coinAcceptOrderBO;

	@Override
	public String addCoinAcceptOrder(CoinAcceptOrder data) {
		return coinAcceptOrderBO.saveCoinAcceptOrder(data);
	}

	@Override
	public int editCoinAcceptOrder(CoinAcceptOrder data) {
		if (!coinAcceptOrderBO.isCoinAcceptOrderExist(data.getCode())) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return coinAcceptOrderBO.refreshCoinAcceptOrder(data);
	}

	@Override
	public int dropCoinAcceptOrder(String code) {
		if (!coinAcceptOrderBO.isCoinAcceptOrderExist(code)) {
			throw new BizException("xn0000", "记录编号不存在");
		}
		return coinAcceptOrderBO.removeCoinAcceptOrder(code);
	}

	@Override
	public Paginable<CoinAcceptOrder> queryCoinAcceptOrderPage(int start, int limit,
			CoinAcceptOrder condition) {
		return coinAcceptOrderBO.getPaginable(start, limit, condition);
	}

	@Override
	public List<CoinAcceptOrder> queryCoinAcceptOrderList(CoinAcceptOrder condition) {
		return coinAcceptOrderBO.queryCoinAcceptOrderList(condition);
	}

	@Override
	public CoinAcceptOrder getCoinAcceptOrder(String code) {
		return coinAcceptOrderBO.getCoinAcceptOrder(code);
	}
}