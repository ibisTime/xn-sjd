package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GiftOrder;

/**
 * 礼物订单
 * @author: silver 
 * @since: Oct 6, 2018 10:41:52 PM 
 * @history:
 */
public interface IGiftOrderBO extends IPaginableBO<GiftOrder> {

    public boolean isGiftOrderExist(String code);

    public String saveGiftOrder(GiftOrder data);

    public int removeGiftOrder(String code);

    public int refreshGiftOrder(GiftOrder data);

    // 更新过期礼物
    public void refreshExpireGift(String code);

    public List<GiftOrder> queryGiftOrderList(GiftOrder condition);

    public GiftOrder getGiftOrder(String code);

}
