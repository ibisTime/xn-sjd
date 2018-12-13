package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN629433Req;
import com.ogc.standard.dto.req.XN629433ReqLogistics;
import com.ogc.standard.enums.EDeriveGroupStatus;
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

    @Autowired
    private IUserBO userBO;

    @Override
    @Transactional
    public String directSales(String code, String userMobile, BigDecimal price,
            Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.TO_ADOPT.getCode()
            .equals(originalGroup.getStatus())
                && !EOriginalGroupStatus.ADOPTING.getCode()
                    .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        User claimant = userBO.getUserByMobile(userMobile);
        if (originalGroup.getOwnerId().equals(claimant.getUserId())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不能转让给自己");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        String deriveGroupCode = deriveGroupBO.saveDirectSales(code, price,
            quantity, claimant.getUserId());

        // 更新数量和总价
        Integer nowQuantity = originalGroup.getQuantity() - quantity;
        BigDecimal nowPrice = originalGroup.getPrice()
            .subtract(price.multiply(new BigDecimal(quantity)));

        originalGroupBO.refreshQuantityPrice(code, nowQuantity, nowPrice);

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(code,
            originalGroup.getPresellQuantity() + quantity);

        return deriveGroupCode;
    }

    @Override
    @Transactional
    public String qrSales(String code, BigDecimal price, Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.TO_ADOPT.getCode()
            .equals(originalGroup.getStatus())
                && !EOriginalGroupStatus.ADOPTING.getCode()
                    .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        String deriveGroupCode = deriveGroupBO.saveQrSales(code, price,
            quantity);

        // 更新数量和总价
        Integer nowQuantity = originalGroup.getQuantity() - quantity;
        BigDecimal nowPrice = originalGroup.getPrice()
            .subtract(price.multiply(new BigDecimal(quantity)));

        originalGroupBO.refreshQuantityPrice(code, nowQuantity, nowPrice);

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(code,
            originalGroup.getPresellQuantity() + quantity);

        return deriveGroupCode;
    }

    @Override
    @Transactional
    public String publicSales(String code, BigDecimal price, Integer quantity) {
        OriginalGroup originalGroup = originalGroupBO.getOriginalGroup(code);
        if (!EOriginalGroupStatus.TO_ADOPT.getCode()
            .equals(originalGroup.getStatus())
                && !EOriginalGroupStatus.ADOPTING.getCode()
                    .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可转让状态，不能转让");
        }

        if (originalGroup.getQuantity() < quantity) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "资产数量不足");
        }

        // 添加派生组
        String deriveGroupCode = deriveGroupBO.savePublicSales(code, price,
            quantity);

        // 更新数量和总价
        Integer nowQuantity = originalGroup.getQuantity() - quantity;
        BigDecimal nowPrice = originalGroup.getPrice()
            .subtract(price.multiply(new BigDecimal(quantity)));

        originalGroupBO.refreshQuantityPrice(code, nowQuantity, nowPrice);

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(code,
            originalGroup.getPresellQuantity() + quantity);

        return deriveGroupCode;
    }

    @Override
    @Transactional
    public void fillAddress(XN629433Req req) {
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(req.getCode());
        if (!EOriginalGroupStatus.TO_RECEIVE.getCode()
            .equals(originalGroup.getStatus())
                && !EOriginalGroupStatus.ADOPTING.getCode()
                    .equals(originalGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可填写地址状态");
        }

        // 添加物流单
        Integer totalCount = 0;
        if (CollectionUtils.isNotEmpty(req.getLogisticsList())) {
            for (XN629433ReqLogistics logistics : req.getLogisticsList()) {
                totalCount += StringValidater
                    .toInteger(logistics.getDeliverCount());
                if (totalCount > originalGroup.getQuantity()) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "发货数量大于资产数量，请重新填写");
                }

                presellLogisticsBO.savePresellLogistics(req.getCode(),
                    logistics);
            }
        }

        // 更新数量
        originalGroupBO.refreshQuantity(originalGroup.getCode(),
            originalGroup.getQuantity() - totalCount);

        // 更新提货中数量
        originalGroupBO.refreshReceivingQuantity(originalGroup.getCode(),
            originalGroup.getReceivingQuantity() + totalCount);

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
        endCondition.setDeliverDatetimeEnd(DateUtil.getTodayStart());
        List<OriginalGroup> endList = originalGroupBO
            .queryOriginalGroupList(endCondition);

        if (CollectionUtils.isNotEmpty(endList)) {
            for (OriginalGroup originalGroup : endList) {
                originalGroupBO.refreshEndAdopt(originalGroup.getCode());

                // 到发货时间回收寄售
                recoverDerive(originalGroup);
            }
        }
        logger.info("***************结束扫描已结束认养资产***************");
    }

    private void recoverDerive(OriginalGroup originalGroup) {
        List<DeriveGroup> list = deriveGroupBO.queryDeriveGroupListByOriginal(
            originalGroup.getCode(), EDeriveGroupStatus.TO_CLAIM.getCode());

        if (CollectionUtils.isNotEmpty(list)) {
            for (DeriveGroup deriveGroup : list) {
                List<PresellInventory> presellInventorieList = presellInventoryBO
                    .queryPresellInventoryListByGroup(deriveGroup.getCode());

                if (CollectionUtils.isNotEmpty(presellInventorieList)) {
                    for (PresellInventory presellInventory : presellInventorieList) {
                        presellInventoryBO.refreshGroup(
                            presellInventory.getCode(),
                            EGroupType.ORIGINAL_GROUP.getCode(),
                            originalGroup.getCode());
                    }
                }

                deriveGroupBO.refreshQuantity(deriveGroup.getCode(), 0);
            }
        }
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

        // 树木列表
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryTreeNumberListByGroup(originalGroup.getCode());
        List<String> treeNumberList = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                String[] tmp = presellInventory.getTreeNumber().split("&");
                for (String treeNumber : tmp) {

                    if (!treeNumberList.contains(treeNumber)) {
                        treeNumberList.add(treeNumber);
                    }

                }
            }
        }
        originalGroup.setTreeNumberList(treeNumberList);

        // 所属人
        User ownerInfo = userBO.getUserUnCheck(originalGroup.getOwnerId());
        originalGroup.setOwnerInfo(ownerInfo);
    }

}
