package com.ogc.standard.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuMatchResultHistoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuMatchResultHistoryDAO;
import com.ogc.standard.domain.SimuMatchResultHistory;

@Component
public class SimuMatchResultHistoryBOImpl
        extends PaginableBOImpl<SimuMatchResultHistory>
        implements ISimuMatchResultHistoryBO {

    @Autowired
    private ISimuMatchResultHistoryDAO simuMatchResultHistoryDAO;

    @Override
    public String saveSimuMatchResultHistory(SimuMatchResultHistory data) {
        String code = null;
        if (data != null) {
            simuMatchResultHistoryDAO.insert(data);
        }

        return code;
    }

    @Override
    public List<SimuMatchResultHistory> querySimuMatchResultHistoryList(
            SimuMatchResultHistory condition) {

        return simuMatchResultHistoryDAO.selectList(condition);
    }

}
