package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.dto.req.XN629050Req;
import com.ogc.standard.dto.req.XN629051Req;

@Component
public interface IGroupAdoptOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 第一人下单
    public String firstAddGroupAdoptOrder(XN629050Req req);

    // 非第一人下单
    public String unFirstAddGroupAdoptOrder(XN629051Req req);

    public int dropGroupAdoptOrder(String code);

    public int editGroupAdoptOrder(GroupAdoptOrder data);

    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

    // 取消订单
    public void cancelGroupAdoptOrder(String code);

    // 支付订单
    public String payGroupAdoptOrder(String code, String type);

}
