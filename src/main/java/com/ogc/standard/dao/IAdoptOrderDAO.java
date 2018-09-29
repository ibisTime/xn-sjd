package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdoptOrder;

//dao层 
public interface IAdoptOrderDAO extends IBaseDAO<AdoptOrder> {
    String NAMESPACE = IAdoptOrderDAO.class.getName().concat(".");

    int update(AdoptOrder data);

    // 取消订单
    void updateCancelAdoptOrder(AdoptOrder data);

    // 支付订单
    void updatepayAdoptOrder(AdoptOrder data);
}
