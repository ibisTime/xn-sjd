package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICompanyDAO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN630061Req;
import com.ogc.standard.dto.req.XN630063Req;
import com.ogc.standard.dto.req.XN630080Req;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.req.XN730073Req;
import com.ogc.standard.dto.req.XN730090Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company>
        implements ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    @Transactional
    public String saveCompany(XN730072Req req, String userId) {
        Company data = new Company();
        String code = OrderNoGenerater.generate(EGeneratePrefix.GS.getCode());
        data.setCode(code);
        data.setUserId(userId);
        data.setName(req.getName());

        data.setCharger(req.getCharger());
        data.setChargeMobile(req.getChargeMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        data.setAddress(req.getAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());
        data.setOrganizationCode(req.getOrganizationCode());
        data.setUpdater(req.getUpdater());

        data.setUpdateDatetime(new Date());
        companyDAO.insert(data);

        // 更新用户公司
        sysUserBO.refreshCompany(userId, code);

        return code;
    }

    @Override
    @Transactional
    public String saveCompany(XN630063Req req, String userId) {
        Company data = new Company();
        String code = OrderNoGenerater.generate(EGeneratePrefix.GS.getCode());
        data.setCode(code);
        data.setUserId(userId);
        data.setName(req.getCompanyName());
        data.setCharger(req.getCompanyCharger());
        data.setChargeMobile(req.getChargerMobile());
        data.setAddress(req.getCompanyAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());
        data.setOrganizationCode(req.getOrganizationCode());
        data.setCertificateTemplate(req.getCertificateTemplate());
        Date date = new Date();
        data.setCreateDatetime(date);
        data.setUpdater(userId);
        data.setUpdateDatetime(date);
        companyDAO.insert(data);

        // 更新用户公司
        sysUserBO.refreshCompany(userId, code);

        return code;
    }

    @Override
    @Transactional
    public String saveCompany(String userId) {
        String code = null;
        if (StringUtils.isNotBlank(userId)) {
            Company data = new Company();
            code = OrderNoGenerater.generate(EGeneratePrefix.GS.getCode());
            data.setCode(code);
            data.setUserId(userId);
            data.setCreateDatetime(new Date());
            companyDAO.insert(data);

            // 更新用户公司
            sysUserBO.refreshCompany(userId, code);
        }
        return code;
    }

    @Override
    public void refreshCompany(XN630061Req req) {
        Company data = getCompanyByUserId(req.getUserId());
        data.setName(req.getCompanyName());
        data.setCharger(req.getCompanyCharger());
        data.setChargeMobile(req.getChargerMobile());
        data.setAddress(req.getCompanyAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());
        data.setOrganizationCode(req.getOrganizationCode());
        data.setUpdater(req.getUserId());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        companyDAO.update(data);
    }

    @Override
    public void refreshCompany(XN730073Req req) {
        Company data = getCompanyByUserId(req.getUserId());
        data.setName(req.getName());
        data.setCharger(req.getCharger());
        data.setChargeMobile(req.getChargeMobile());
        data.setProvince(req.getProvince());

        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());

        data.setOrganizationCode(req.getOrganizationCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        companyDAO.update(data);
    }

    @Override
    public void refreshCompany(XN730090Req req) {
        Company data = getCompanyByUserId(req.getUserId());
        data.setName(req.getName());
        data.setCharger(req.getCharger());
        data.setChargeMobile(req.getChargeMobile());
        data.setProvince(req.getProvince());

        data.setCity(req.getCity());
        data.setArea(req.getArea());
        data.setAddress(req.getAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());

        data.setOrganizationCode(req.getOrganizationCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        companyDAO.update(data);
    }

    @Override
    public void refreshCompany(XN630080Req req) {
        Company data = getCompanyByUserId(req.getUserId());
        data.setName(req.getCompanyName());
        data.setCharger(req.getCompanyCharger());
        data.setChargeMobile(req.getChargerMobile());

        data.setAddress(req.getCompanyAddress());
        data.setDescription(req.getDescription());
        data.setBussinessLicense(req.getBussinessLicense());

        data.setCertificateTemplate(req.getCertificateTemplate());
        data.setContractTemplate(req.getContractTemplate());
        data.setOrganizationCode(req.getOrganizationCode());
        data.setUpdateDatetime(new Date());
        companyDAO.updateOwnerCompanyInfo(data);
    }

    @Override
    public int refreshCompanyInfo(Company data, String bussinessLicense,
            String certificateTemplate, String contractTemplate) {
        data.setBussinessLicense(bussinessLicense);
        data.setCertificateTemplate(certificateTemplate);
        data.setContractTemplate(contractTemplate);
        data.setUpdater(data.getUserId());
        data.setUpdateDatetime(new Date());
        return companyDAO.updateInfo(data);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public Company getCompany(String code) {
        Company data = null;
        if (StringUtils.isNotBlank(code)) {
            Company condition = new Company();
            condition.setCode(code);
            data = companyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司不存在");
            }
        }
        return data;
    }

    @Override
    public Company getCompanyByUserId(String userId) {
        Company condition = new Company();
        condition.setUserId(userId);
        List<Company> list = queryCompanyList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn0000", "公司不存在");
        }
        return list.get(0);
    }

}
