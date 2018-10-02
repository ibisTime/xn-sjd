package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629048Res;

public interface IAdoptOrderBO extends IPaginableBO<AdoptOrder> {

    public String saveAdoptOrder(User user, Product product,
            ProductSpecs productSpecs, int quantity);

    // 取消订单
    public void cancelAdoptOrder(AdoptOrder data, String remark);

    // 余额支付订单
    public void payYueSuccess(AdoptOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount);

    // 三方支付预支付
    public void refreshPayGroup(AdoptOrder data, String payType,
            XN629048Res resultRes);

    // 三方支付成功
    public void paySuccess(AdoptOrder data, BigDecimal payAmount,
            BigDecimal backJfAmount);

    public List<AdoptOrder> queryAdoptOrderList(AdoptOrder condition);

    public AdoptOrder getAdoptOrder(String code);

    public XN629048Res getOrderDkAmount(AdoptOrder data, String isDk);

}
