package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Coin;

public interface ICoinDAO extends IBaseDAO<Coin> {
    String NAMESPACE = ICoinDAO.class.getName().concat(".");

    public int update(Coin data);

    public int updateStatus(Coin data);
}
