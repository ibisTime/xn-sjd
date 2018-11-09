package com.ogc.standard.ao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.bo.IDeriveGroupBO;
import com.ogc.standard.bo.IGroupOrderBO;
import com.ogc.standard.bo.IOriginalGroupBO;
import com.ogc.standard.bo.IPresellInventoryBO;
import com.ogc.standard.bo.IPresellProductBO;
import com.ogc.standard.bo.base.Page;
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

    @Autowired
    private IGroupOrderBO groupOrderBO;

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

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(originalGroup.getCode(),
            originalGroup.getPresellQuantity() - deriveGroup.getQuantity());

    }

    @Override
    @Transactional
    public String claimDirect(String code, String claimant) {
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

        // 落地订单
        String orderCode = groupOrderBO.saveGroupOrder(deriveGroup, claimant);

        return orderCode;
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

        // 更新数量
        originalGroupBO.refreshQuantity(originalGroup.getCode(),
            originalGroup.getQuantity() + deriveGroup.getQuantity());

        // 更新寄售数量
        originalGroupBO.refreshPresellQuantity(originalGroup.getCode(),
            originalGroup.getPresellQuantity() - deriveGroup.getQuantity());
    }

    @Override
    @Transactional
    public String claimQr(String code, String claimant) {
        DeriveGroup deriveGroup = deriveGroupBO.getDeriveGroup(code);

        if (!EDeriveGroupStatus.TO_CLAIM.getCode()
            .equals(deriveGroup.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "资产不是待认领状态，不能认领");
        }

        // 落地订单
        String orderCode = groupOrderBO.saveGroupOrder(deriveGroup, claimant);

        return orderCode;
    }

    @Override
    @Transactional
    public String claimPublic(String code, String claimant, Integer quantity) {
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

        if (claimant.equals(deriveGroup.getCreater())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "无法认领自己发布的挂单寄售");
        }

        // 落地订单
        String orderCode = groupOrderBO.saveGroupOrder(deriveGroup, quantity,
            claimant);

        // 更新数量
        deriveGroupBO.refreshQuantity(code,
            deriveGroup.getQuantity() - quantity);

        return orderCode;
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
    public Paginable<DeriveGroup> queryToclaimDeriveGroupPage(
            DeriveGroup condition) {
        List<DeriveGroup> list = new ArrayList<DeriveGroup>();

        // 我发布的寄售
        List<DeriveGroup> myDeriveGroupList = deriveGroupBO
            .queryDeriveGroupListByCreater(condition.getCreater(),
                EDeriveGroupStatus.TO_CLAIM.getCode());
        list.addAll(myDeriveGroupList);

        // 别人转给我的定向
        List<DeriveGroup> myDirectDeriveGroupList = deriveGroupBO
            .queryDeriveGroupListDirect(condition.getCreater(),
                EDeriveGroupStatus.TO_CLAIM.getCode());
        list.addAll(myDirectDeriveGroupList);

        if (CollectionUtils.isNotEmpty(list)) {
            for (DeriveGroup deriveGroup : list) {
                init(deriveGroup);
            }
        }

        ListSort(list);

        Paginable<DeriveGroup> page = new Page<DeriveGroup>(1, list.size(),
            list.size());
        page.setList(list);

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
    public List<DeriveGroup> queryVarietyList(DeriveGroup data) {
        return deriveGroupBO.queryVarietyList(data);
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

        // 树木列表
        List<PresellInventory> treeNumberList = presellInventoryBO
            .queryTreeNumberListByGroup(deriveGroup.getCode());
        deriveGroup.setTreeNumberList(treeNumberList);
    }

    // 将List按照日期排序
    public void ListSort(List<DeriveGroup> list) {
        Collections.sort(list, new Comparator<DeriveGroup>() {
            @Override
            public int compare(DeriveGroup o1, DeriveGroup o2) {

                if (o1.getCreateDatetime().getTime() > o2.getCreateDatetime()
                    .getTime()) {
                    return -1;
                } else if (o1.getCreateDatetime().getTime() < o2
                    .getCreateDatetime().getTime()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

}
