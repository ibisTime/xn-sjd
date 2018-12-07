package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN630080Req;

public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public Company getCompanyByUser(String userId);

    public void completeCompanyOwner(String userId, String bussinessLicense,
            String certificateTemplate, String contractTemplate);

    public void refreshCompanyOwner(XN630080Req req);

}
