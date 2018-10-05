package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AdoptOrderTree;

//dao层 
public interface IAdoptOrderTreeDAO extends IBaseDAO<AdoptOrderTree> {
    String NAMESPACE = IAdoptOrderTreeDAO.class.getName().concat(".");

    int updateStatus(AdoptOrderTree data);
}
