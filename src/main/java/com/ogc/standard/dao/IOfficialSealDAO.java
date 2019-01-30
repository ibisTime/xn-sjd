package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.OfficialSeal;

public interface IOfficialSealDAO extends IBaseDAO<OfficialSeal> {
    String NAMESPACE = IOfficialSealDAO.class.getName().concat(".");

    int updateOfficialSeal(OfficialSeal officialSeal);

}
