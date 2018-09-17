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
    public List<SimuKLine> querySimuKLineList(String symbol, String toSymbol,
            String period) {

        SimuKLine condition = new SimuKLine();
        condition.setSymbol(symbol);
        condition.setToSymbol(toSymbol);
        condition.setPeriod(period);
        condition.setOrder("create_datetime asc");

        return simuKLineBO.querySimuKLineList(condition);
    }
}
