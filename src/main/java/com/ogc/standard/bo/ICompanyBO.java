package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Company;

public interface ICompanyBO extends IPaginableBO<Company> {

    public boolean isCompanyExist(String code);

    public String saveCompany(Company data);

    public int removeCompany(String code);

    public int refreshCompany(Company data);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

}
