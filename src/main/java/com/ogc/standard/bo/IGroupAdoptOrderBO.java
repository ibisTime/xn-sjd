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

}
