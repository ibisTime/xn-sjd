package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Company;

public interface ICompanyDAO extends IBaseDAO<Company> {
    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    int update(Company data);

    int updateInfo(Company data);

    int updateOwnerCompanyInfo(Company data);
}
