/**
 * @Title AfterSaleBOImpl.java 
 * @Package com.ogc.standard.bo.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午8:12:11 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAfterSaleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IAfterSaleDAO;
import com.ogc.standard.domain.AfterSale;
import com.ogc.standard.enums.EAfterSaleStatus;
import com.ogc.standard.enums.EAfterSaleType;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午8:12:11 
 * @history:
 */
@Component
public class AfterSaleBOImpl extends PaginableBOImpl<AfterSale> implements
        IAfterSaleBO {

    @Autowired
    private IAfterSaleDAO afterSaleDAO;

    @Override
    public String saveAfterSale(String orderDetailCode,
            String logisticsCompany, String logisticsNumber,
            BigDecimal refundAmount, String deliver) {
        AfterSale data = new AfterSale();
        String code = OrderNoGenerater.generate(EGeneratePrefix.AfterSale
            .getCode());
        data.setCode(code);
        data.setOrderDetailCode(orderDetailCode);
        data.setType(EAfterSaleType.goodsMoney.getCode());
        data.setStatus(EAfterSaleStatus.TOHANDLE.getCode());
        data.setRefundAmount(refundAmount);
        data.setLogisticsCompany(logisticsCompany);
        data.setLogisticsNumber(logisticsNumber);
        data.setDeliver(deliver);
        data.setDeliverDatetime(new Date());
        afterSaleDAO.insert(data);
        return code;
    }

    @Override
    public String AfterSaleNoGoods(String orderDetailCode,
            BigDecimal refundAmount) {
        AfterSale data = new AfterSale();
        String code = OrderNoGenerater.generate(EGeneratePrefix.AfterSale
            .getCode());
        data.setCode(code);
        data.setOrderDetailCode(orderDetailCode);
        data.setType(EAfterSaleType.onlyMoney.getCode());
        data.setStatus(EAfterSaleStatus.TOHANDLE.getCode());
        data.setRefundAmount(refundAmount);
        afterSaleDAO.insert(data);
        return code;
    }

    @Override
    public void refreshHandle(AfterSale data, String status) {
        data.setStatus(status);
        afterSaleDAO.updateHandle(data);
    }

    @Override
    public void refreshReceive(AfterSale data, String receiver) {
        data.setReceiver(receiver);
        data.setReceiverDatetime(new Date());
        data.setStatus(EAfterSaleStatus.FINISH.getCode());
        afterSaleDAO.updateReceive(data);
    }

    @Override
    public List<AfterSale> queryShList(AfterSale condition) {
        return afterSaleDAO.selectList(condition);
    }

    @Override
    public AfterSale getAfterSale(String code) {
        AfterSale condition = new AfterSale();
        condition.setCode(code);
        AfterSale data = afterSaleDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "该售后订单不存在");
        }
        return data;
    }

    @Override
    public boolean isAftrSaleExist(String orderDetailCode) {
        AfterSale condition = new AfterSale();
        condition.setOrderDetailCode(orderDetailCode);
        List<String> statusList = new ArrayList<String>();
        statusList.add(EAfterSaleStatus.FINISH.getCode());
        statusList.add(EAfterSaleStatus.PASS.getCode());
        statusList.add(EAfterSaleStatus.TOHANDLE.getCode());
        condition.setStatusList(statusList);
        if (afterSaleDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

}
