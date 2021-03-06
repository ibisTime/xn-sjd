package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IApplyBindMaintainBO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN629600Req;
import com.ogc.standard.dto.req.XN629602Req;
import com.ogc.standard.enums.EApplyBindMaintainStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class ApplyBindMaintainAOImpl implements IApplyBindMaintainAO {

    @Autowired
    private IApplyBindMaintainBO applyBindMaintainBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ITreeBO treeBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public String applyBindMaintain(XN629600Req req) {
        sysUserBO.getSYSUser(req.getMaintainId());
        sysUserBO.getSYSUser(req.getOwnerId());

        // 判断是否已绑定养护方
        String maintainId = applyBindMaintainBO.getMaintainId(req.getOwnerId());
        if (StringUtils.isNotBlank(maintainId)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "您已绑定养护方");
        }
        return applyBindMaintainBO.saveApplyBindMaintain(req.getOwnerId(),
            req.getMaintainId(), req.getUpdater(), req.getRemark());
    }

    @Override
    public void approveApplyBindMaintain(XN629602Req req) {
        ApplyBindMaintain data = applyBindMaintainBO
            .getApplyBindMaintain(req.getCode());
        if (!EApplyBindMaintainStatus.TO_APPROVE.getCode()
            .equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前申请记录不是待审核");
        }

        if (EBoolean.YES.getCode().equals(req.getApproveResult())) {
            data.setStatus(EApplyBindMaintainStatus.BIND.getCode());
        } else {
            data.setStatus(EApplyBindMaintainStatus.NO_PASS.getCode());
        }
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        applyBindMaintainBO.approveApplyBindMaintain(data);
    }

    @Override
    public void reBingMaintain(String code, String maintainId, String updater,
            String remark) {
        applyBindMaintainBO.reBindMaintain(code, maintainId, updater, remark);
    }

    @Override
    public Paginable<ApplyBindMaintain> queryApplyBindMaintainPage(int start,
            int limit, ApplyBindMaintain condition) {
        Paginable<ApplyBindMaintain> paginable = applyBindMaintainBO
            .getPaginable(start, limit, condition);
        List<ApplyBindMaintain> list = paginable.getList();
        for (ApplyBindMaintain data : list) {
            init(data);
        }
        return paginable;
    }

    @Override
    public List<ApplyBindMaintain> queryApplyBindMaintainList(
            ApplyBindMaintain condition) {
        List<ApplyBindMaintain> list = applyBindMaintainBO
            .queryApplyBindMaintainList(condition);
        for (ApplyBindMaintain data : list) {
            init(data);
        }
        return list;
    }

    @Override
    public ApplyBindMaintain getApplyBindMaintain(String code) {
        ApplyBindMaintain data = applyBindMaintainBO.getApplyBindMaintain(code);
        init(data);
        return data;
    }

    private void init(ApplyBindMaintain data) {
        // 产权方用户
        SYSUser ownerUser = sysUserBO.getSYSUserUnCheck(data.getOwnerId());
        data.setOwnerUser(ownerUser);
        data.setOwnerTreeCount(
            treeBO.getTotalCountByOwnerId(data.getOwnerId()));

        if (null != ownerUser) {
            if (StringUtils.isNotBlank(ownerUser.getCompanyCode())) {
                Company ownerCompany = companyBO
                    .getCompany(ownerUser.getCompanyCode());
                ownerUser.setCompany(ownerCompany);
            }
        }

        // 养护方用户
        SYSUser maintainUser = sysUserBO
            .getSYSUserUnCheck(data.getMaintainId());
        data.setMaintainUser(maintainUser);
        if (null != maintainUser) {
            if (StringUtils.isNotBlank(maintainUser.getCompanyCode())) {
                Company maintainCompany = companyBO
                    .getCompany(maintainUser.getCompanyCode());
                maintainUser.setCompany(maintainCompany);
            }
        }

        // 更新人
        String updaterName = null;
        SYSUser updater = sysUserBO.getSYSUserUnCheck(data.getUpdater());
        if (null != updater) {
            updaterName = updater.getLoginName();
            if (StringUtils.isNotBlank(updater.getRealName())) {
                updaterName = updater.getRealName().concat("-")
                    .concat(updaterName);
            }
        }
        data.setUpdaterName(updaterName);

        // 养护方人民币账户
        Account maintainCnyAccount = accountBO
            .getAccountByUser(data.getMaintainId(), ECurrency.CNY.getCode());
        data.setMaintainCnyAccount(maintainCnyAccount);
    }

}
