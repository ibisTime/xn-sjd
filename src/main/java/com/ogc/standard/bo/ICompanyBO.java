package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.req.XN730073Req;

public interface ICompanyBO extends IPaginableBO<Company> {

    public String saveCompany(XN730072Req req, String userId);

    public void refreshCompany(XN730073Req req);

    public String saveCompany(Company data);

    public int refreshCompany(Company data);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUserId(String userId);

}
