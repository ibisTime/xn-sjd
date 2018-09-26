package com.ogc.standard.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ICompanyBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ICompanyDAO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.exception.BizException;

@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {

    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public boolean isCompanyExist(String code) {
        Company condition = new Company();
        condition.setCode(code);
        if (companyDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveCompany(Company data) {
        String code = null;
        if (data != null) {
            // code = OrderNoGenerater.generateM(EGeneratePrefix.CT.getCode());
            data.setCode(code);
            companyDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeCompany(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Company data = new Company();
            data.setCode(code);
            count = companyDAO.delete(data);
        }
        return count;
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
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
