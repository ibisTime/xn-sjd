package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.domain.PresellInventory;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.enums.EDeriveGroupStatus;
import com.ogc.standard.enums.EGroupType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class DeriveGroupAOImpl implements IDeriveGroupAO {

    @Autowired
    private IDeriveGroupBO deriveGroupBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Autowired
    private IPresellInventoryBO presellInventoryBO;

    @Autowired
    private IPresellProductBO presellProductBO;

    @Override
    @Transactional
    public void revock(String code, String claimant, String remark) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是待认领状态，不能撤销");
        }

        if (!claimant.equals(deriveGroup.getCreater())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "您不是挂单人，不能撤销");
        }

        deriveGroupBO.refreshRevock(code, claimant, remark);

        // 收回预售权
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(deriveGroup.getOriginalCode());
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryPresellInventoryListByGroup(deriveGroup.getCode());

        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                presellInventoryBO.refreshGroup(presellInventory.getCode(),
                    EGroupType.ORIGINAL_GROUP.getCode(),
                    originalGroup.getCode());
            }
        }

        // 更新数量
        originalGroupBO.refreshQuantity(originalGroup.getCode(),
            originalGroup.getQuantity() + deriveGroup.getQuantity());

    }

    @Override
    @Transactional
    public void claimDirect(String code, String claimant) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是待认领状态，不能认领");
        }

        if (!claimant.equals(deriveGroup.getClaimant())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "您不是定向认领人，不能认领");
        }

        // 认领寄售
        deriveGroupBO.refreshClaimDirect(code);

        // 生成新的资产
        originalGroupBO.saveOriginalGroup(deriveGroup.getOriginalCode(),
            claimant, deriveGroup.getPrice(), deriveGroup.getQuantity());

    }

    @Override
    @Transactional
    public void rejectDirect(String code, String claimant, String remark) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是可拒绝状态，不能拒绝");
        }

        if (!claimant.equals(deriveGroup.getClaimant())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "您不是定向认领人，不能拒绝");
        }

        // 拒绝寄售
        deriveGroupBO.refreshRejectDirect(code, remark);

        // 收回预售权
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(deriveGroup.getOriginalCode());
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryPresellInventoryListByGroup(deriveGroup.getCode());

        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                presellInventoryBO.refreshGroup(presellInventory.getCode(),
                    EGroupType.ORIGINAL_GROUP.getCode(),
                    originalGroup.getCode());
            }
        }
    }

    @Override
    @Transactional
    public void claimQr(String code, String claimant) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是待认领状态，不能认领");
        }

        // 认领寄售
        deriveGroupBO.refreshClaimQr(code, claimant);

        // 生成新的资产
        String originalGroupCode = originalGroupBO.saveOriginalGroup(
            deriveGroup.getOriginalCode(), claimant, deriveGroup.getPrice(),
            deriveGroup.getQuantity());

        // 分配预售权
        int presellInventoryQuantity = 0;
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(deriveGroup.getOriginalCode());
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryPresellInventoryListByGroup(originalGroup.getCode());

        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                if (++presellInventoryQuantity > deriveGroup.getQuantity()) {
                    break;
                }

                presellInventoryBO.refreshGroup(presellInventory.getCode(),
                    EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
            }
        }
    }

    @Override
    @Transactional
    public void claimPublic(String code, String claimant, Integer quantity) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是待认领状态，不能认领");
        }

        if (quantity > deriveGroup.getQuantity()) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "认领数量大于剩余数量，不能认领");
        }

        // 认领寄售
        Integer remainQuantity = deriveGroup.getQuantity() - quantity;
        String status = EDeriveGroupStatus.TO_CLAIM.getCode();
        if (remainQuantity == 0) {
            status = EDeriveGroupStatus.CLAIMED.getCode();
        }
        deriveGroupBO.refreshClaimPublic(code, claimant, remainQuantity,
            status);

        // 生成新的资产
        String originalGroupCode = originalGroupBO.saveOriginalGroup(
            deriveGroup.getOriginalCode(), claimant, deriveGroup.getPrice(),
            deriveGroup.getQuantity());

        // 分配预售权
        int presellInventoryQuantity = 0;
        OriginalGroup originalGroup = originalGroupBO
            .getOriginalGroup(deriveGroup.getOriginalCode());
        List<PresellInventory> presellInventorieList = presellInventoryBO
            .queryPresellInventoryListByGroup(originalGroup.getCode());

        if (CollectionUtils.isNotEmpty(presellInventorieList)) {
            for (PresellInventory presellInventory : presellInventorieList) {
                if (++presellInventoryQuantity > deriveGroup.getQuantity()) {
                    break;
                }

                presellInventoryBO.refreshGroup(presellInventory.getCode(),
                    EGroupType.ORIGINAL_GROUP.getCode(), originalGroupCode);
            }
        }
    }

    @Override
    public Paginable<DeriveGroup> queryDeriveGroupPage(int start, int limit,
            DeriveGroup condition) {
        Paginable<DeriveGroup> page = deriveGroupBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (DeriveGroup deriveGroup : page.getList()) {
                init(deriveGroup);
            }
        }

        return page;
    }

    @Override
    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition) {
        List<DeriveGroup> list = deriveGroupBO.queryDeriveGroupList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (DeriveGroup deriveGroup : list) {
                init(deriveGroup);
            }
        }

        return list;
    }

    @Override
    public DeriveGroup getDeriveGroup(String code) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        init(deriveGroup);

        return deriveGroup;
    }

    private void init(DeriveGroup deriveGroup) {
        // 预售产品
        PresellProduct presellProduct = presellProductBO
            .getPresellProduct(deriveGroup.getProductCode());
        deriveGroup.setPresellProduct(presellProduct);
    }
}
