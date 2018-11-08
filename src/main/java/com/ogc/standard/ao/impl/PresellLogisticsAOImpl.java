package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IPresellLogisticsAO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellLogisticsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellLogistics;
import com.ogc.standard.enums.EPresellLogisticsStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class PresellLogisticsAOImpl implements IPresellLogisticsAO {

    @Autowired
    private IPresellLogisticsBO presellLogisticsBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Override
    public void send(String code, String deliver, String logisticsCompany,
            String logisticsNumber) {
        PresellLogistics presellLogistics = presellLogisticsBO
            .getPresellLogistics(code);

        if (!EPresellLogisticsStatus.TO_SEND.getCode()
            .equals(presellLogistics.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "物流单不是待发货状态，不能发货");
        }

        presellLogisticsBO.refreshSend(code, deliver, logisticsCompany,
            logisticsNumber);
    }

    @Override
    @Transactional
    public void confirmReceive(String code) {
        PresellLogistics presellLogistics = presellLogisticsBO
            .getPresellLogistics(code);

        if (!EPresellLogisticsStatus.TO_RECEIVE.getCode()
            .equals(presellLogistics.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "物流单不是待收货状态，不能收货");
        }

        presellLogisticsBO.refreshConfirmReceive(code);

        originalGroupBO.refreshReceive(presellLogistics.getOriginalGroupCode());

        // 更新提货中数量
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(presellLogistics.getOriginalGroupCode());
        originalGroupBO.refreshReceivingQuantity(originalGroup.getCode(),
            originalGroup.getReceivingQuantity()
                    - presellLogistics.getDeliverCount());
    }

    @Override
    public Paginable<PresellLogistics> queryPresellLogisticsPage(int start,
            int limit, PresellLogistics condition) {
        return presellLogisticsBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<PresellLogistics> queryPresellLogisticsList(
            PresellLogistics condition) {
        return presellLogisticsBO.queryPresellLogisticsList(condition);
    }

    @Override
    public PresellLogistics getPresellLogistics(String code) {
        return presellLogisticsBO.getPresellLogistics(code);
    }

}
