package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630080Req;
import com.ogc.standard.enums.ESYSUserKind;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public void completeCompanyOwner(String userId, String bussinessLicense,
            String certificateTemplate, String contractTemplate) {
        SYSUser sysUser = sysUserBO.getSYSUser(userId);
        if (!ESYSUserKind.OWNER.getCode().equals(sysUser.getKind())
                && !ESYSUserKind.BUSINESS.getCode().equals(sysUser.getKind())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "不是产权端或商家端用户，不能操作");
        }

        Company company = companyBO.getCompanyByUserId(sysUser.getUserId());
        companyBO.refreshCompanyInfo(company, bussinessLicense,
            certificateTemplate, contractTemplate);

        if (ESYSUserKind.BUSINESS.getCode().equals(sysUser.getKind())) {
            sysUserBO.refreshStatus(userId, ESYSUserStatus.TO_APPROVE.getCode(),
                userId, null);
        }
    }

    @Override
    public void refreshCompanyOwner(XN630080Req req) {
        SYSUser sysUser = sysUserBO.getSYSUser(req.getUserId());

        // if (!ESYSUserKind.OWNER.getCode().equals(sysUser.getKind())) {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
        // "不是产权端用户，不能操作");
        // }

        companyBO.refreshCompany(req);

        sysUserBO.refreshStatus(req.getUserId(), sysUser.getStatus(),
            req.getUserId(), req.getRemark());
    }

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company getCompany(String code) {
        return companyBO.getCompany(code);
    }

    @Override
    public Company getCompanyByUser(String userId) {
        return companyBO.getCompanyByUserId(userId);
    }

}
