package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GiftOrder;

/**
 * 礼物订单
 * @author: silver 
 * @since: Oct 6, 2018 10:43:41 PM 
 * @history:
 */
public interface IGiftOrderDAO extends IBaseDAO<GiftOrder> {
    String NAMESPACE = IGiftOrderDAO.class.getName().concat(".");

    int update(GiftOrder data);

    // 更新过期状态
    public int updateExpireGift(GiftOrder data);
}
