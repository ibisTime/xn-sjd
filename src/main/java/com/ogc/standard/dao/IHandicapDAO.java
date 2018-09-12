package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Handicap;

public interface IHandicapDAO extends IBaseDAO<Handicap> {
    String NAMESPACE = IHandicapDAO.class.getName().concat(".");

    public int update(Handicap data);
}
