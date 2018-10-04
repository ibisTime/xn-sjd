package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;

public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

    public void completeCompanyOwner(String userId, String certificateTemplate,
            String contractTemplate);

}
