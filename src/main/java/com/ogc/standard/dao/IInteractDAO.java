package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Interact;

public interface IInteractDAO extends IBaseDAO<Interact> {
    String NAMESPACE = IInteractDAO.class.getName().concat(".");

}
