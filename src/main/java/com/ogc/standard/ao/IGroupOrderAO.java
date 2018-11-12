package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupOrder;

@Component
public interface IGroupOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 取消订单
    public void cancelGroupOrder(String code, String remark);

    // 支付订单
    public Object toPayGroupOrder(String code, String payType, String tradePwd);

    // 支付订单
    public Object toPayDonateGroupOrder(String code);

    // 支付成功订单回调处理
    public void paySuccess(String payGroup);

    // 定时取消未支付订单
    public void doCancelGroupOrder();

    public Paginable<GroupOrder> queryGroupOrderPage(int start, int limit,
            GroupOrder condition);

    public List<GroupOrder> queryGroupOrderList(GroupOrder condition);

    public GroupOrder getGroupOrder(String code);

}
