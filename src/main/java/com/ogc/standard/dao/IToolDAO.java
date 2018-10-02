package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Tool;

//dao层 
public interface IToolDAO extends IBaseDAO<Tool> {
    String NAMESPACE = IToolDAO.class.getName().concat(".");

    public int updateStatus(Tool condition);
}
