package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.GroupCoin;

public interface IGroupCoinBO extends IPaginableBO<GroupCoin> {

    public GroupCoin distributeAccount(String userId, String groupCode,
            String symbol, BigDecimal initAmount, BigDecimal totalAssets,
            Double rate);

    public void removeGroupCoin(String groupCode, String symbol);

    public int refreshGroupCoin(String groupCode, String symbol);

    public List<GroupCoin> queryGroupCoinListByGroupCode(String groupCode);

    public GroupCoin getGroupCoin(String groupCode, String userId,
            String symbol);

    public GroupCoin checkAccountAndDistribute(String userId, String groupCode,
            String symbol);

    public GroupCoin frozenCount(GroupCoin groupCoinAccount,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo);

    public GroupCoin unfrozenAmount(GroupCoin groupCoinAccount,
            BigDecimal unfreezeAmount, String bizType, String bizNote,
            String refNo);

    public GroupCoin changeAmount(GroupCoin dbAccount, BigDecimal transAmount,
            String refNo, String bizType, String bizNote);
}
