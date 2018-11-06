package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupOrder;

public interface IGroupOrderDAO extends IBaseDAO<GroupOrder> {
    String NAMESPACE = IGroupOrderDAO.class.getName().concat(".");

    // 取消订单
    public int updateCancelOrder(GroupOrder data);

    // 余额支付
    public int updatePayYueSuccess(GroupOrder data);

    // 预支付更新
    public int updatePayGroup(GroupOrder data);

    // 支付回调成功
    public int updatePaySuccess(GroupOrder data);
}
