package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupCoin;
import com.ogc.standard.domain.GroupCoinJour;

public interface IGroupCoinJourBO extends IPaginableBO<GroupCoinJour> {

    public String addJour(GroupCoin dbAccount, String refNo, String bizType,
            String bizNote, BigDecimal transAmount);

    public String addFrozenJour(GroupCoin dbAccount, String refNo,
            String bizType, String bizNote, BigDecimal transCount);

    public List<GroupCoinJour> queryGroupCoinJourList(GroupCoinJour condition);

}
