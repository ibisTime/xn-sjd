package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISimuOrderDetailBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISimuOrderDetailDAO;
import com.ogc.standard.domain.SimuOrderDetail;
import com.ogc.standard.exception.BizException;

@Component
public class SimuOrderDetailBOImpl extends PaginableBOImpl<SimuOrderDetail>
        implements ISimuOrderDetailBO {

    @Autowired
    private ISimuOrderDetailDAO simuOrderDetailDAO;

    @Override
    public int saveSimuOrderDetail(SimuOrderDetail data) {
        int count = 0;
        if (data != null) {
            count = simuOrderDetailDAO.insert(data);
        }
        return 0;
    }

    @Override
    public List<SimuOrderDetail> querySimuOrderDetailList(
            SimuOrderDetail condition) {
        return simuOrderDetailDAO.selectList(condition);
    }

    @Override
    public SimuOrderDetail getSimuOrderDetail(String code) {
        SimuOrderDetail data = null;
        if (StringUtils.isNotBlank(code)) {
            SimuOrderDetail condition = new SimuOrderDetail();
            condition.setCode(code);
            data = simuOrderDetailDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "委托单成交明细记录不存在");
            }
        }
        return data;
    }
}
