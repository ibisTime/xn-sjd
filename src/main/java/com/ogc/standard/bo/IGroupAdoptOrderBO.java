package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupAdoptOrder;

public interface IGroupAdoptOrderBO extends IPaginableBO<GroupAdoptOrder> {

    public boolean isGroupAdoptOrderExist(String code);

    public String saveGroupAdoptOrder(GroupAdoptOrder data);

    public int removeGroupAdoptOrder(String code);

    public int refreshGroupAdoptOrder(GroupAdoptOrder data);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

    public GroupAdoptOrder getGroupAdoptOrderByIdentifyCode(String identifyCode);

    // 第一人下单
    public String saveGroupAdoptOrderFirst(GroupAdoptOrder data);

    // 非第一人下单
    public String saveGroupAdoptOrderUnFirst(GroupAdoptOrder data);

    // 取消订单
    public void refreshCancelGroupAdoptOrder(GroupAdoptOrder data);

    // 支付订单
    public void payGroupAdoptOrder(GroupAdoptOrder data);

}
