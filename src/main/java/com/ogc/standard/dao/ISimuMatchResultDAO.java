package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SimuMatchResult;

public interface ISimuMatchResultDAO extends IBaseDAO<SimuMatchResult> {
    String NAMESPACE = ISimuMatchResultDAO.class.getName().concat(".");

    public int update(SimuMatchResult data);
}
