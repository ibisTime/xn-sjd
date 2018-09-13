package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.TokenEvent;

public interface ITokenEventDAO extends IBaseDAO<TokenEvent> {
    String NAMESPACE = ITokenEventDAO.class.getName().concat(".");

    public void insertEventList(List<TokenEvent> tokenEventList);
}
