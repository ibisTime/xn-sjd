package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Arbitrate;

public interface IArbitrateDAO extends IBaseDAO<Arbitrate> {
    String NAMESPACE = IArbitrateDAO.class.getName().concat(".");

    public int updateHandle(Arbitrate data);

    public int updateCancel(Arbitrate data);
}
