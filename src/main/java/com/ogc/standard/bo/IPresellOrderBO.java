package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.PresellSpecs;
import com.ogc.standard.dto.res.XN629048Res;

public interface IPresellOrderBO extends IPaginableBO<PresellOrder> {

    public String savePresellOrder(String userId, PresellProduct presellProduct,
            PresellSpecs presellSpecs, Integer quantity);

    // 取消订单
    public void cancelPresellOrder(String code, String remark);

    // 余额支付订单
    public void payYueSuccess(String code, XN629048Res resultRes,
            BigDecimal backjfAmount);

    // 三方支付预支付
    public void refreshPayGroup(String code, String payType,
            XN629048Res resultRes);

    // 三方支付成功
    public void paySuccess(String code, BigDecimal payAmount,
            BigDecimal backJfAmount);

    public List<PresellOrder> queryPresellOrderList(PresellOrder condition);

    public PresellOrder getPresellOrder(String code);

}
