package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IPresellLogisticsBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dao.IPresellLogisticsDAO;
import com.ogc.standard.domain.PresellLogistics;
import com.ogc.standard.dto.req.XN629433ReqLogistics;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.EPresellLogisticsStatus;
import com.ogc.standard.exception.BizException;

@Component
public class PresellLogisticsBOImpl extends PaginableBOImpl<PresellLogistics>
        implements IPresellLogisticsBO {

    @Autowired
    private IPresellLogisticsDAO presellLogisticsDAO;

    @Override
    public String savePresellLogistics(String originalGroupCode,
            XN629433ReqLogistics logistics) {
        PresellLogistics data = new PresellLogistics();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.PRESELL_LOGISTICS.getCode());

        data.setCode(code);
        data.setOriginalGroupCode(originalGroupCode);
        data.setDeliverCount(
            StringValidater.toInteger(logistics.getDeliverCount()));
        data.setReceiver(logistics.getReceiver());
        data.setReceiverMobile(logistics.getReceiverMobile());
        data.setStatus(EPresellLogisticsStatus.TO_SEND.getCode());

        data.setProvince(logistics.getProvince());
        data.setCity(logistics.getCity());
        data.setArea(logistics.getArea());
        data.setAddress(logistics.getAddress());
        data.setRemark(logistics.getRemark());

        presellLogisticsDAO.insert(data);
        return code;

    }

    @Override
    public void refreshSend(String code, String deliver,
            String logisticsCompany, String logisticsNumber) {
        PresellLogistics data = new PresellLogistics();

        data.setCode(code);
        data.setDeliver(deliver);
        data.setDeliverDatetime(new Date());
        data.setLogisticsCompany(logisticsCompany);
        data.setLogisticsNumber(logisticsNumber);
        data.setStatus(EPresellLogisticsStatus.TO_RECEIVE.getCode());

        presellLogisticsDAO.updateSendLogistisc(data);
    }

    @Override
    public void refreshConfirmReceive(String code) {
        PresellLogistics data = new PresellLogistics();

        data.setCode(code);
        data.setReceiverDatetime(new Date());
        data.setStatus(EPresellLogisticsStatus.RECEIVED.getCode());

        presellLogisticsDAO.updateRceiveLogistisc(data);
    }

    @Override
    public List<PresellLogistics> queryPresellLogisticsList(
            PresellLogistics condition) {
        return presellLogisticsDAO.selectList(condition);
    }

    @Override
    public PresellLogistics getPresellLogistics(String code) {
        PresellLogistics data = null;
        if (StringUtils.isNotBlank(code)) {
            PresellLogistics condition = new PresellLogistics();
            condition.setCode(code);
            data = presellLogisticsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "预售物流单不存在");
            }
        }
        return data;
    }

}
