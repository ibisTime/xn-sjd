package com.ogc.standard.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Company;
import com.ogc.standard.dto.req.XN630300Req;
import com.ogc.standard.dto.req.XN630302Req;

@Component
public interface ICompanyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addCompany(XN630300Req req);

    public int dropCompany(String code);

    public int editCompany(XN630302Req req);

    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition);

    public List<Company> queryCompanyList(Company condition);

    public Company getCompany(String code);

}
