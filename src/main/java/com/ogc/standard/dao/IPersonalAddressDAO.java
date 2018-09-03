package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.PersonalAddress;

public interface IPersonalAddressDAO extends IBaseDAO<PersonalAddress> {
    String NAMESPACE = IPersonalAddressDAO.class.getName().concat(".");
}
