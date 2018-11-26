package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;
import com.ogc.standard.dto.res.XN629048Res;

public interface IGroupAdoptOrderBO extends IPaginableBO<GroupAdoptOrder> {

    // 第一人下单
    public String saveGroupAdoptOrderFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs);

    // 非第一人下单
    public String saveGroupAdoptOrderUnFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs);

    // 取消订单
    public void refreshCancelOrder(GroupAdoptOrder data, String remark);

    // 余额支付订单
    public void payYueSuccess(GroupAdoptOrder data, XN629048Res resultRes,
            BigDecimal backjfAmount);

    // 三方支付预支付
    public void refreshPayGroup(GroupAdoptOrder data, String payType,
            XN629048Res resultRes);

    // 三方支付成功
    public void paySuccess(GroupAdoptOrder data, BigDecimal payAmount,
            BigDecimal backJfAmount);

    // 更新已满标订单状态
    public void refreshFullOrder(String code);

    // 更新未满标订单状态
    public void refreshUnFullOrder(String code);

    // 更新已满标订单状态
    public void refreshFullOrderById(String id);

    // 更新未满标订单状态
    public void refreshUnFullOrderById(String id);

    // 开始认养
    public void refreshStartAdopt(String code);

    // 结束认养
    public void refreshEndAdopt(String code);

    // 结算
    public void refreshSettle(GroupAdoptOrder data, String approveResult,
            String updater, String remark);

    // 根据识别码获取订单
    public List<GroupAdoptOrder> queryGroupAdoptOrderById(String identifyCode);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

    public GroupAdoptOrder getGroupAdoptOrderByIdentifyCode(
            String identifyCode);

    public GroupAdoptOrder getGroupAdoptOrderByPayGroup(String payGroup);

}
