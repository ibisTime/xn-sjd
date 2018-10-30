package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.dto.res.XN629048Res;

public interface IGroupAdoptOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 第一人下单
    public String firstAddGroupAdoptOrder(String userId, String specsCode,
            Integer quantity);

    // 非第一人下单
    public String unFirstAddGroupAdoptOrder(String identifyCode, String userId,
            Integer quantity);

    // 取消订单
    public void cancelGroupAdoptOrder(String code, String userId,
            String remark);

    // 支付订单
    public Object toPayAdoptOrder(String code, String payType,
            String isJfDeduct, String tradePwd);

    // 更新已满标的订单
    public void toFullAdopt();

    // 获取订单抵扣金额
    public XN629048Res getOrderDkAmount(String code);

    // 支付成功订单回调处理
    public void paySuccess(String payGroup);

    // 更新未支付订单状态
    public void doCancelGroupAdoptOrder();

    // 更新已失效的识别码
    public void doInvalidIdentifyCode();

    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

}
