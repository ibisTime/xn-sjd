package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuOrderHistoryBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuOrderHistoryDAO;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.domain.SimuOrderHistory;

@Component
public class SimuOrderHistoryBOImpl extends PaginableBOImpl<SimuOrderHistory>
        implements ISimuOrderHistoryBO {

    @Autowired
    private ISimuOrderHistoryDAO simuOrderHistoryDAO;

    @Override
    public String saveSimuOrderHistory(SimuOrder simuOrder) {

        // 落地委托单
        SimuOrderHistory data = new SimuOrderHistory();
        data.setCode(simuOrder.getCode());
        data.setUserId(simuOrder.getUserId());
        data.setSymbol(simuOrder.getSymbol());
        data.setToSymbol(simuOrder.getToSymbol());
        data.setType(simuOrder.getType());

        data.setDirection(simuOrder.getDirection());
        data.setPrice(simuOrder.getPrice());
        data.setTotalCount(simuOrder.getTotalCount());
        data.setTotalAmount(simuOrder.getTotalAmount());
        data.setTradedCount(simuOrder.getTradedCount());

        data.setTradedAmount(simuOrder.getTradedAmount());
        data.setTradedFee(simuOrder.getTradedFee());
        data.setAvgPrice(simuOrder.getAvgPrice());
        if (null != simuOrder.getLastTradedDatetime()) {
            data.setLastTradedDatetime(simuOrder.getLastTradedDatetime());
        }
        data.setCreateDatetime(simuOrder.getCreateDatetime());

        if (null != simuOrder.getCancelDatetime()) {
            data.setCancelDatetime(simuOrder.getCancelDatetime());
        }
        data.setStatus(simuOrder.getStatus());

        simuOrderHistoryDAO.insert(data);
        return data.getCode();
    }

    @Override
    public List<SimuOrderHistory> querySimuOrderHistoryList(
            SimuOrderHistory condition) {
        return simuOrderHistoryDAO.selectList(condition);
    }

    @Override
    public SimuOrderHistory getSimuOrderHistory(String code) {
        SimuOrderHistory data = null;
        if (StringUtils.isNotBlank(code)) {
            SimuOrderHistory condition = new SimuOrderHistory();
            condition.setCode(code);
            data = simuOrderHistoryDAO.select(condition);

        }
        return data;
    }

}
