package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISimuOrderHistoryAO;
import com.ogc.standard.bo.ISimuOrderHistoryBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrderHistory;

@Service
public class SimuOrderHistoryAOImpl implements ISimuOrderHistoryAO {

    @Autowired
    private ISimuOrderHistoryBO simuOrderHistoryBO;

    @Override
    public Paginable<SimuOrderHistory> querySimuOrderHistoryPage(int start,
            int limit, SimuOrderHistory condition) {
        return simuOrderHistoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SimuOrderHistory> querySimuOrderHistoryList(
            SimuOrderHistory condition) {
        return simuOrderHistoryBO.querySimuOrderHistoryList(condition);
    }

}
