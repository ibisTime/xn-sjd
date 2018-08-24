package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGroupCoinJourAO;
import com.ogc.standard.bo.IGroupCoinJourBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.GroupCoinJour;

@Service
public class GroupCoinJourAOImpl implements IGroupCoinJourAO {

    @Autowired
    private IGroupCoinJourBO groupCoinJourBO;

    @Override
    public Paginable<GroupCoinJour> queryGroupCoinJourPage(int start, int limit,
            GroupCoinJour condition) {
        return groupCoinJourBO.getPaginable(start, limit, condition);
    }

}
