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

    public String addGroupAdoptOrder(XN629050Req req);

    public int dropGroupAdoptOrder(String code);

    public int editGroupAdoptOrder(GroupAdoptOrder data);

    public Paginable<GroupAdoptOrder> queryGroupAdoptOrderPage(int start,
            int limit, GroupAdoptOrder condition);

    public List<GroupAdoptOrder> queryGroupAdoptOrderList(
            GroupAdoptOrder condition);

    public GroupAdoptOrder getGroupAdoptOrder(String code);

    public String unFirstAddGroupAdoptOrder(XN629051Req req);

    public void cancelGroupAdoptOrder(String code);

}
