package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISimuKLineAO;
import com.ogc.standard.bo.ISimuKLineBO;
import com.ogc.standard.domain.SimuKLine;

@Service
public class SimuKLineAOImpl implements ISimuKLineAO {

    @Autowired
    private ISimuKLineBO simuKLineBO;

    @Override
    public SimuKLine addSimuKLine(SimuKLine data) {
        return simuKLineBO.saveSimuKLine(data);
    }

    @Override
    public List<SimuKLine> querySimuKLineList(SimuKLine condition) {
        return simuKLineBO.querySimuKLineList(condition);
    }

}
