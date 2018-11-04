package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.enums.EDeriveGroupStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class DeriveGroupAOImpl implements IDeriveGroupAO {

    @Autowired
    private IDeriveGroupBO deriveGroupBO;

    @Autowired
    private IOriginalGroupBO originalGroupBO;

    @Override
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
        originalGroupBO.saveOriginalGroup(code, claimant,
            deriveGroup.getPrice(), deriveGroup.getQuantity());

    }

    @Override
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
        originalGroupBO.saveOriginalGroup(code, claimant,
            deriveGroup.getPrice(), deriveGroup.getQuantity());
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
        originalGroupBO.saveOriginalGroup(code, claimant,
            deriveGroup.getPrice(), deriveGroup.getQuantity());
    }

    @Override
    public Paginable<DeriveGroup> queryDeriveGroupPage(int start, int limit,
            DeriveGroup condition) {
        return deriveGroupBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<DeriveGroup> queryDeriveGroupList(DeriveGroup condition) {
        return deriveGroupBO.queryDeriveGroupList(condition);
    }

    @Override
    public DeriveGroup getDeriveGroup(String code) {
        return deriveGroupBO.getDeriveGroup(code);
    }

}
