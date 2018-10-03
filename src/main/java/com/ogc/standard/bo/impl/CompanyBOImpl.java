package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ICompanyDAO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.req.XN730073Req;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
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
        return code;
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
    public String saveCompany(Company data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.GS.getCode());
            data.setCode(code);
            companyDAO.insert(data);
        }
        return code;
    }

    @Override
    public int refreshCompany(Company data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = companyDAO.update(data);
        }
        return count;
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
