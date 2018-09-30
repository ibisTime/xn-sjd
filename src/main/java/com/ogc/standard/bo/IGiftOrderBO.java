package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GiftOrder;



//CHECK ��鲢��ע�� 
public interface IGiftOrderBO extends IPaginableBO<GiftOrder> {


	public boolean isGiftOrderExist(String code);


	public String saveGiftOrder(GiftOrder data);


	public int removeGiftOrder(String code);


	public int refreshGiftOrder(GiftOrder data);


	public List<GiftOrder> queryGiftOrderList(GiftOrder condition);


	public GiftOrder getGiftOrder(String code);


}