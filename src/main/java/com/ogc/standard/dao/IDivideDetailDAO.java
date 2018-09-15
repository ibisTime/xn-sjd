package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.DivideDetail;

public interface IDivideDetailDAO extends IBaseDAO<DivideDetail> {

    String NAMESPACE = IDivideDetailDAO.class.getName().concat(".");

    public int update(DivideDetail data);
}
