package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellLogisticsBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.dto.req.XN629433Req;
import com.ogc.standard.dto.req.XN629433ReqLogistics;

@Service
public class OriginalGroupAOImpl implements IOriginalGroupAO {

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IDeriveGroupBO deriveGroupBO;

    @Autowired
    private IPresellInventoryBO presellInventoryBO;

    @Autowired
    private IPresellLogisticsBO presellLogisticsBO;

    @Override
    @Transactional
    public void directSales(String code, String userId, BigDecimal price,
            Integer quantity) {

        // 添加派生组
        deriveGroupBO.saveDirectSales(code, price, quantity, userId);

        // 转移预售权

    }

    @Override
    @Transactional
    public void qrSales(String code, BigDecimal price, Integer quantity) {
        // 添加派生组
        deriveGroupBO.saveQrSales(code, price, quantity);

    }

    @Override
    @Transactional
    public void publicSales(String code, BigDecimal price, Integer quantity) {
        // 添加派生组
        deriveGroupBO.savePublicSales(code, price, quantity);
    }

    @Override
    @Transactional
    public void fillAddress(XN629433Req req) {

        // 添加物流单
        if (CollectionUtils.isNotEmpty(req.getLogisticsList())) {
            for (XN629433ReqLogistics logistics : req.getLogisticsList()) {
                presellLogisticsBO.savePresellLogistics(req.getCode(),
                    logistics);
            }
        }
    }

    @Override
    public Paginable<OriginalGroup> queryOriginalGroupPage(int start, int limit,
            OriginalGroup condition) {
        return originalGroupBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition) {
        return originalGroupBO.queryOriginalGroupList(condition);
    }

    @Override
    public OriginalGroup getOriginalGroup(String code) {
        return originalGroupBO.getOriginalGroup(code);
    }

}
