package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.GroupAdoptOrder;

//dao层 
public interface IGroupAdoptOrderDAO extends IBaseDAO<GroupAdoptOrder> {
    String NAMESPACE = IGroupAdoptOrderDAO.class.getName().concat(".");

    // 第一人下单
    int insertFirst(GroupAdoptOrder data);

    // 非第一人下单
    int insertUnFirst(GroupAdoptOrder data);

    // 取消订单
    void updateCancelGroupAdoptOrder(GroupAdoptOrder data);

    // 余额支付订单
    void updatePayYueSuccess(GroupAdoptOrder data);

    // 更新支付组号
    public int updatePayGroup(GroupAdoptOrder data);

    // 更新已满标订单
    public int updateFullOrder(GroupAdoptOrder data);

    // 更新未满标订单
    public int updateUnFullOrder(GroupAdoptOrder data);

    // 更新已满标订单
    public int updateFullOrderById(GroupAdoptOrder data);

    // 更新未满标订单
    public int updateUnFullOrderById(GroupAdoptOrder data);

    // 开始认养
    public int updateStartAdopt(GroupAdoptOrder data);

    // 结束认养
    public int updateEndAdopt(GroupAdoptOrder data);

    // 更新结算状态
    public int updateSettleStatus(GroupAdoptOrder data);

    // 三方支付成功
    public int updatePaySuccess(GroupAdoptOrder data);

    // 查询已支付的下单数量
    public long selectPayedTotalQuantity(GroupAdoptOrder data);

}
