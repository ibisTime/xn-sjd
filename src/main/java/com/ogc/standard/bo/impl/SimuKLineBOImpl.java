package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuKLineBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuKLineDAO;
import com.ogc.standard.domain.SimuKLine;

@Component
public class SimuKLineBOImpl extends PaginableBOImpl<SimuKLine>
        implements ISimuKLineBO {

    @Autowired
    private ISimuKLineDAO simuKLineDAO;

    @Override
    public SimuKLine saveSimuKLine(SimuKLine data) {
        if (data != null) {
            simuKLineDAO.insert(data);
        }
        return data;
    }

    @Override
    public List<SimuKLine> querySimuKLineList(SimuKLine condition) {
        return simuKLineDAO.selectList(condition);
    }

}
