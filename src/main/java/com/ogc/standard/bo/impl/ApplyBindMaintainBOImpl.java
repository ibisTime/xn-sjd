package com.ogc.standard.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.IApplyBindMaintainDAO;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629600Req;
import com.ogc.standard.enums.EApplyBindMaintainStatus;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Component
public class ApplyBindMaintainBOImpl extends PaginableBOImpl<ApplyBindMaintain>
        implements IApplyBindMaintainBO {

    @Autowired
    private IApplyBindMaintainDAO applyBindMaintainDAO;

    @Override
    public String saveApplyBindMaintain(XN629600Req req) {
        String code = null;
        if (req != null) {
            ApplyBindMaintain data = new ApplyBindMaintain();
            code = OrderNoGenerater
                .generate(EGeneratePrefix.APPLY_BIND_MAINTAIN.getCode());
            data.setCode(code);
            data.setOwnerId(req.getOwnerId());
            data.setMaintainId(req.getMaintainId());
            data.setStatus(EApplyBindMaintainStatus.TO_APPROVE.getCode());
            data.setUpdater(req.getUpdater());
            data.setUpdateDatetime(new Date());
            data.setRemark(req.getRemark());
            applyBindMaintainDAO.insert(data);
        }
        return code;
    }

    @Override
    public void approveApplyBindMaintain(ApplyBindMaintain data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            applyBindMaintainDAO.approveApplyBindMaintain(data);
        }
    }

    @Override
    public void doCheckBindMaintain(String ownerId) {
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setOwnerId(ownerId);
        condition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
        int count = (int) applyBindMaintainDAO.selectTotalCount(condition);
        if (count < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "请先绑定养护方");
        }
    }

    @Override
    public List<String> queryBindOwnerList(String maintainId) {
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setMaintainId(maintainId);
        condition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
        List<ApplyBindMaintain> list = applyBindMaintainDAO
            .selectList(condition);
        List<String> ownerList = new ArrayList<String>();
        for (ApplyBindMaintain applyBindMaintain : list) {
            ownerList.add(applyBindMaintain.getOwnerId());
        }
        return ownerList;
    }

    @Override
    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition) {
        return applyBindMaintainDAO.selectList(condition);
    }

    @Override
    public ApplyBindMaintain getApplyBindMaintain(String code) {
        ApplyBindMaintain data = null;
        if (StringUtils.isNotBlank(code)) {
            ApplyBindMaintain condition = new ApplyBindMaintain();
            condition.setCode(code);
            data = applyBindMaintainDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "申请绑定养护方记录不存在");
            }
        }
        return data;
    }

    @Override
    public String getMaintainId(String ownerId) {
        String maintainId = null;
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setOwnerId(ownerId);
        condition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
        List<ApplyBindMaintain> list = applyBindMaintainDAO
            .selectList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            ApplyBindMaintain data = list.get(0);
            maintainId = data.getMaintainId();
        }
        return maintainId;
    }
}
