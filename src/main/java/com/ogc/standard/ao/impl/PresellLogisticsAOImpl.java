package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ogc.standard.ao.IPresellLogisticsAO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellLogisticsBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellLogistics;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.enums.EPresellLogisticsStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import com.ogc.standard.util.kdniao.KdniaoTrackQueryUtil;

@Service
public class PresellLogisticsAOImpl implements IPresellLogisticsAO {

    @Autowired
    private IPresellLogisticsBO presellLogisticsBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IPresellProductBO presellProductBO;

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

        // 全部收货后更新资产状态
        if (CollectionUtils.isEmpty(presellLogisticsBO.queryUnDelivedByOriginal(
            presellLogistics.getOriginalGroupCode()))) {
            originalGroupBO
                .refreshReceive(presellLogistics.getOriginalGroupCode());
        }

        // 更新提货中数量
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(presellLogistics.getOriginalGroupCode());
        originalGroupBO.refreshReceivingQuantity(originalGroup.getCode(),
            originalGroup.getReceivingQuantity()
                    - presellLogistics.getDeliverCount());

        // 更新已收货数量
        originalGroupBO.refreshReceivedQuantity(originalGroup.getCode(),
            originalGroup.getReceivedQuantity()
                    + presellLogistics.getDeliverCount());

    }

    @Override
    public Object trackQuery(String expCode, String expNo) {
        KdniaoTrackQueryUtil api = new KdniaoTrackQueryUtil();
        Object track = null;

        try {
            String result = api.getOrderTracesByJson(expCode, expNo);

            track = JSONObject.parseObject(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return track;
    }

    @Override
    public Paginable<PresellLogistics> queryPresellLogisticsPage(int start,
            int limit, PresellLogistics condition) {
        Paginable<PresellLogistics> page = presellLogisticsBO
            .getPaginable(start, limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (PresellLogistics presellLogistics : page.getList()) {
                init(presellLogistics);
            }
        }

        return page;
    }

    @Override
    public List<PresellLogistics> queryPresellLogisticsList(
            PresellLogistics condition) {
        List<PresellLogistics> list = presellLogisticsBO
            .queryPresellLogisticsList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (PresellLogistics presellLogistics : list) {
                init(presellLogistics);
            }
        }

        return list;
    }

    @Override
    public PresellLogistics getPresellLogistics(String code) {
        PresellLogistics presellLogistics = presellLogisticsBO
            .getPresellLogistics(code);

        init(presellLogistics);

        return presellLogistics;
    }

    private void init(PresellLogistics presellLogistics) {
        // 包装单位
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(presellLogistics.getOriginalGroupCode());
        if (null != originalGroup) {
            presellLogistics.setUnit(originalGroup.getUnit());
        }

        // 商品信息
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());
        presellLogistics.setPresellProduct(presellProduct);
    }

}
