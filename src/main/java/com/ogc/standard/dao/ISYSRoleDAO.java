package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SYSRole;

public interface ISYSRoleDAO extends IBaseDAO<SYSRole> {
    String NAMESPACE = ISYSRoleDAO.class.getName().concat(".");

    public int update(SYSRole data);
}
