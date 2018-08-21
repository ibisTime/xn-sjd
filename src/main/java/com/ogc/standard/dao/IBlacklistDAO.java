package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Blacklist;

public interface IBlacklistDAO extends IBaseDAO<Blacklist> {
    String NAMESPACE = IBlacklistDAO.class.getName().concat(".");

    int updateStatus(Blacklist data);
}
