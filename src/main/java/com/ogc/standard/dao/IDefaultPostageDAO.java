package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.DefaultPostage;

public interface IDefaultPostageDAO extends IBaseDAO<DefaultPostage> {
    String NAMESPACE = IDefaultPostageDAO.class.getName().concat(".");

    // 修改价格
    public int updatePrice(DefaultPostage defaultPostage);

}
