package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.dto.res.XN629048Res;

public interface IGroupOrderBO extends IPaginableBO<GroupOrder> {
    // 保存订单
    public String saveGroupOrder(DeriveGroup deriveGroup, String applyUser);

    // 保存订单
    public String saveGroupOrder(DeriveGroup deriveGroup, Integer quantity,
            String applyUser);

    // 取消订单
    public void cancelGrouplOrder(String code, String remark);

    // 余额支付订单
    public void payYueSuccess(String code, XN629048Res resultRes,
            BigDecimal backjfAmount);

    // 三方支付预支付
    public void refreshPayGroup(String code, String payType,
            XN629048Res resultRes);

    // 三方支付成功回调
    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount);

    public List<GroupOrder> queryGroupOrderList(GroupOrder condition);

    public GroupOrder getGroupOrder(String code);

}
