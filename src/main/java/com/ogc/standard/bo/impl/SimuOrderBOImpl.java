package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuOrderBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuOrderDAO;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.enums.ESimuOrderDirection;
import com.ogc.standard.enums.ESimuOrderType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class SimuOrderBOImpl extends PaginableBOImpl<SimuOrder>
        implements ISimuOrderBO {

    @Autowired
    private ISimuOrderDAO simuOrderDAO;

    @Override
    public boolean isSimuOrderExist(String code) {
        SimuOrder condition = new SimuOrder();
        condition.setCode(code);
        if (simuOrderDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveSimuOrder(SimuOrder data) {
        if (data != null) {
            simuOrderDAO.insert(data);
        }
    }

    @Override
    public void tradeSuccess(SimuOrder data) {
        if (data != null) {
            simuOrderDAO.tradeSuccess(data);
        }
    }

    @Override
    public int refreshMarketSimuOrder(SimuOrder data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            count = simuOrderDAO.updateMarketSimuOrder(data);
        }
        return count;
    }

    @Override
    public int refreshLimitSimuOrder(SimuOrder data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            count = simuOrderDAO.updateLimitSimuOrder(data);
        }
        return count;
    }

    @Override
    public int cancel(SimuOrder data) {
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getCode())) {
            count = simuOrderDAO.cancel(data);
        }
        return count;
    }

    @Override
    public List<SimuOrder> querySimuOrderList(SimuOrder condition) {
        return simuOrderDAO.selectList(condition);
    }

    @Override
    public List<SimuOrder> queryBidsHandicapList(int handicapQuantity) {
        SimuOrder condition = new SimuOrder();
        condition.setType(ESimuOrderType.LIMIT.getCode());
        condition.setDirection(ESimuOrderDirection.BUY.getCode());

        condition.setOrder("price desc, start_datetime desc");

        return simuOrderDAO.selectList(condition, 1, handicapQuantity);
    }

    @Override
    public List<SimuOrder> queryAsksHandicapList(int handicapQuantity) {
        SimuOrder condition = new SimuOrder();
        condition.setType(ESimuOrderType.LIMIT.getCode());
        condition.setDirection(ESimuOrderDirection.SELL.getCode());

        condition.setOrder("price asc, start_datetime asc");

        return simuOrderDAO.selectList(condition, 1, handicapQuantity);
    }

    @Override
    public SimuOrder getSimuOrder(String code) {
        SimuOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            SimuOrder condition = new SimuOrder();
            condition.setCode(code);
            data = simuOrderDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "委托单号" + code + "不存在");
            }
        }
        return data;
    }

}
