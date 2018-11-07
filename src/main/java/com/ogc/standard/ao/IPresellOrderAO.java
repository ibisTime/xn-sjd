package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellOrder;

@Component
public interface IPresellOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交订单
    public String commitPresellOrder(String userId, String specsCode,
            Integer quantity);

    // 取消订单
    public void cancelPresellOrder(String code, String remark);

    // 支付订单
    public Object toPayPresellOrder(String code, String payType,
            String tradePwd);

    // 支付成功订单回调处理
    public void paySuccess(String payGroup);

    // 定时取消未支付订单
    public void doCancelPresellOrder();

    public Paginable<PresellOrder> queryPresellOrderPage(int start, int limit,
            PresellOrder condition);

    public List<PresellOrder> queryPresellOrderList(PresellOrder condition);

    public PresellOrder getPresellOrder(String code);

}