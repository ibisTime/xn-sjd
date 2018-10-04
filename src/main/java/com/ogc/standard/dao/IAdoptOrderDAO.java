package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdoptOrder;

//dao层 
public interface IAdoptOrderDAO extends IBaseDAO<AdoptOrder> {
    String NAMESPACE = IAdoptOrderDAO.class.getName().concat(".");

    // 取消订单
    void updateCancelAdoptOrder(AdoptOrder data);

    // 余额支付
    void updatePayYueSuccess(AdoptOrder data);

    // 预支付更新
    void updatePayGroup(AdoptOrder data);

    // 支付回调成功
    void updatePaySuccess(AdoptOrder data);

    void updateStartAdopt(AdoptOrder data);

    void updateEndAdopt(AdoptOrder data);

    void updateSettleStatus(AdoptOrder data);
}
