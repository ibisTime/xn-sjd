package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGroupCoinAO;
import com.ogc.standard.bo.IGroupCoinBO;
import com.ogc.standard.domain.GroupCoin;

@Service
public class GroupCoinAOImpl implements IGroupCoinAO {

    @Autowired
    private IGroupCoinBO groupCoinBO;

    @Override
    public List<GroupCoin> queryGroupCoinListByGroupCode(String groupCode) {
        return groupCoinBO.queryGroupCoinListByGroupCode(groupCode);
    }
}
