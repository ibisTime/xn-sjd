package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupCoin;

public interface IGroupCoinBO extends IPaginableBO<GroupCoin> {

    public int saveGroupCoin(GroupCoin data);

    public void removeGroupCoin(String groupCode, String symbol);

    public int refreshGroupCoin(String groupCode, String symbol);

    public List<GroupCoin> queryGroupCoinListByGroupCode(String groupCode);

    public GroupCoin getGroupCoin(String groupCode, String symbol);

}
