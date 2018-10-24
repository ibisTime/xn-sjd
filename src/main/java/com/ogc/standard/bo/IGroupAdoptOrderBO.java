package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Product;
import com.ogc.standard.domain.ProductSpecs;

public interface IGroupAdoptOrderBO extends IPaginableBO<GroupAdoptOrder> {

    // 第一人下单
    public String saveGroupAdoptOrderFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs);

    // 非第一人下单
    public String saveGroupAdoptOrderUnFirst(String identifyCode, String userId,
            Integer quantity, Product product, ProductSpecs productSpecs);

    // 取消订单
    public void refreshCancelOrder(String code, String remark);

    // 支付订单
    public void payGroupAdoptOrder(GroupAdoptOrder data);

    // 根据识别码获取订单
    public List<GroupAdoptOrder> getGroupAdoptOrderById(String identifyCode);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

    public GroupAdoptOrder getGroupAdoptOrderByIdentifyCode(
            String identifyCode);

}
