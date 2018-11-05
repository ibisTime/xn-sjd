package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PresellOrder;

public interface IPresellOrderDAO extends IBaseDAO<PresellOrder> {
    String NAMESPACE = IPresellOrderDAO.class.getName().concat(".");

    // 取消订单
    public int updateCancelOrder(PresellOrder data);

    // 余额支付
    public int updatePayYueSuccess(PresellOrder data);

    // 预支付更新
    public int updatePayGroup(PresellOrder data);

    // 支付回调成功
    public int updatePaySuccess(PresellOrder data);
}
