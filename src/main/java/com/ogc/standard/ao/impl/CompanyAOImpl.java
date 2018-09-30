package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630300Req;
import com.ogc.standard.dto.req.XN630302Req;
import com.ogc.standard.enums.ESYSUserStatus;
import com.ogc.standard.exception.BizException;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public String addCompany(XN630300Req req) {
        SYSUser user = sysUserBO.getSYSUser(req.getUserId());
        if (ESYSUserStatus.TO_FILL_IN.getCode().equals(user.getStatus())) {
            throw new BizException("xn0000", "不是待填写公司资料状态，不能操作");
        }
        sysUserBO.refreshStatus(user.getUserId(), ESYSUserStatus.TO_APPROVE,
            req.getUserId(), null);
        Company data = new Company();
        data.setUserId(user.getUserId());
        data.setName(req.getCompanyName());
        data.setCharger(req.getCompanyCharger());
        data.setChargeMobile(req.getChargerMobile());
        data.setAddress(req.getCompanyAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());
        data.setOrganizationCode(req.getOrganizationCode());
        data.setCertificateTemplate(req.getCertificateTemplate());
        data.setContractTemplate(req.getContractTemplate());
        data.setCreateDatetime(new Date());
        data.setUpdater(req.getUserId());
        data.setUpdateDatetime(new Date());
        return companyBO.saveCompany(data);
    }

    @Override
    public int editCompany(XN630302Req req) {
        Company condition = new Company();
        condition.setUserId(req.getUserId());
        List<Company> list = companyBO.queryCompanyList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn0000", "公司不存在");
        }
        Company data = list.get(0);
        data.setBussinessLicense(req.getBussinessLicense());
        data.setCertificateTemplate(req.getCertificateTemplate());
        data.setChargeMobile(req.getChargerMobile());
        data.setAddress(req.getCompanyAddress());
        data.setCharger(req.getCompanyCharger());
        data.setName(req.getCompanyName());
        data.setContractTemplate(req.getContractTemplate());
        data.setDescription(req.getDescription());
        data.setUpdateDatetime(new Date());
        data.setUpdater(req.getUserId());
        return companyBO.refreshCompany(data);
    }

    @Override
    public int dropCompany(String code) {
        if (!companyBO.isCompanyExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return companyBO.removeCompany(code);
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
}
