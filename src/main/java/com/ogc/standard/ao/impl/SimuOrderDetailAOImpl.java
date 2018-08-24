package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISimuOrderDetailAO;
import com.ogc.standard.bo.ISimuOrderDetailBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SimuOrderDetail;

@Service
public class SimuOrderDetailAOImpl implements ISimuOrderDetailAO {

    @Autowired
    private ISimuOrderDetailBO simuOrderDetailBO;

    @Override
    public Paginable<SimuOrderDetail> querySimuOrderDetailPage(int start,
            int limit, SimuOrderDetail condition) {
        return simuOrderDetailBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SimuOrderDetail> querySimuOrderDetailList(
            SimuOrderDetail condition) {
        return simuOrderDetailBO.querySimuOrderDetailList(condition);
    }

    @Override
    public SimuOrderDetail getSimuOrderDetail(String code) {
        return simuOrderDetailBO.getSimuOrderDetail(code);
    }

}
