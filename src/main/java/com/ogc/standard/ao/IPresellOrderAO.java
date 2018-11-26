package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.dto.res.XN629048Res;

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
            String tradePwd, String isJfDeduct);

    // 支付成功订单回调处理
    public void paySuccess(String payGroup);

    // 定时取消未支付订单
    public void doCancelPresellOrder();

    // 获取订单抵扣金额
    public XN629048Res getOrderDkAmount(String code);

    public Paginable<PresellOrder> queryPresellOrderPage(int start, int limit,
            PresellOrder condition);

    // 根据产品查订单
    public List<PresellOrder> queryPresellOrderListByProduct(
            String productCode);

    public List<PresellOrder> queryPresellOrderList(PresellOrder condition);

    public PresellOrder getPresellOrder(String code, String isSettle);

}
