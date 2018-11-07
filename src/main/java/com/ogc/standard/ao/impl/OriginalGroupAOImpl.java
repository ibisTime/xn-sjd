package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellLogisticsBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.req.XN629433Req;
import com.ogc.standard.dto.req.XN629433ReqLogistics;
import com.ogc.standard.enums.EGroupType;
import com.ogc.standard.enums.EOriginalGroupStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class OriginalGroupAOImpl implements IOriginalGroupAO {
    static final Logger logger = LoggerFactory
        .getLogger(OriginalGroupAOImpl.class);

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IDeriveGroupBO deriveGroupBO;

    @Autowired
    private IPresellInventoryBO presellInventoryBO;

    @Autowired
    private IPresellLogisticsBO presellLogisticsBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Override
    @Transactional
    public void directSales(String code, String userId, BigDecimal price,
            Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.ADOPTING.getCode()
            .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        if (originalGroup.getOwnerId().equals(userId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不能转让给自己");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        String deriveGroupCode = deriveGroupBO.saveDirectSales(code, price,
            quantity, userId);

        // 转移预售权
        int presellInventoryQuantity = 0;
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryPresellInventoryListByGroup(code);
        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                if (++presellInventoryQuantity > quantity) {
                    break;
                }

                presellInventoryBO.refreshGroup(presellInventory.getCode(),
                    EGroupType.DERIVE_GROUP.getCode(), deriveGroupCode);
            }
        }

        // 更新数量
        originalGroupBO.refreshQuantity(code,
            originalGroup.getQuantity() - quantity);

    }

    @Override
    @Transactional
    public void qrSales(String code, BigDecimal price, Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.ADOPTING.getCode()
            .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        deriveGroupBO.saveQrSales(code, price, quantity);

        // 更新数量
        originalGroupBO.refreshQuantity(code,
            originalGroup.getQuantity() - quantity);
    }

    @Override
    @Transactional
    public void publicSales(String code, BigDecimal price, Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.ADOPTING.getCode()
            .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        deriveGroupBO.savePublicSales(code, price, quantity);

        // 更新数量
        originalGroupBO.refreshQuantity(code,
            originalGroup.getQuantity() - quantity);
    }

    @Override
    @Transactional
    public void fillAddress(XN629433Req req) {
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(req.getCode());
        if (!EOriginalGroupStatus.TO_RECEIVE.getCode()
            .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可填写地址状态");
        }

        // 添加物流单
        if (CollectionUtils.isNotEmpty(req.getLogisticsList())) {
            for (XN629433ReqLogistics logistics : req.getLogisticsList()) {
                presellLogisticsBO.savePresellLogistics(req.getCode(),
                    logistics);
            }
        }
    }

    public void doDailyOriginalGroup() {
        logger.info("***************开始扫描待认养资产***************");
        OriginalGroup startCondition = new OriginalGroup();
        startCondition.setStatus(EOriginalGroupStatus.TO_ADOPT.getCode());
        startCondition.setAdoptStartEndDatetime(DateUtil.getTodayEnd());
        List<OriginalGroup> startList = originalGroupBO
            .queryOriginalGroupList(startCondition);

        if (CollectionUtils.isNotEmpty(startList)) {
            for (OriginalGroup originalGroup : startList) {
                originalGroupBO.refreshStartAdopt(originalGroup.getCode());
            }
        }
        logger.info("***************结束扫描待认养资产***************");

        logger.info("***************开始扫描已结束认养资产***************");
        OriginalGroup endCondition = new OriginalGroup();
        endCondition.setStatus(EOriginalGroupStatus.ADOPTING.getCode());
        endCondition.setAdoptEndEndDatetime(DateUtil.getTodayStart());
        List<OriginalGroup> endList = originalGroupBO
            .queryOriginalGroupList(endCondition);

        if (CollectionUtils.isNotEmpty(endList)) {
            for (OriginalGroup originalGroup : endList) {
                originalGroupBO.refreshEndAdopt(originalGroup.getCode());
            }
        }
        logger.info("***************结束扫描已结束认养资产***************");
    }

    @Override
    public Paginable<OriginalGroup> queryOriginalGroupPage(int start, int limit,
            OriginalGroup condition) {
        Paginable<OriginalGroup> page = originalGroupBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (OriginalGroup originalGroup : page.getList()) {
                init(originalGroup);
            }
        }

        return page;
    }

    @Override
    public List<OriginalGroup> queryOriginalGroupList(OriginalGroup condition) {
        List<OriginalGroup> list = originalGroupBO
            .queryOriginalGroupList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (OriginalGroup originalGroup : list) {
                init(originalGroup);
            }
        }

        return list;
    }

    @Override
    public OriginalGroup getOriginalGroup(String code) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);

        init(originalGroup);

        return originalGroup;
    }

    private void init(OriginalGroup originalGroup) {
        // 预售产品
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(originalGroup.getProductCode());
        originalGroup.setPresellProduct(presellProduct);
    }

}