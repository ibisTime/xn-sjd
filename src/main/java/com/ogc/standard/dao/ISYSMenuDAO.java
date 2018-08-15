package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SYSMenu;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午6:54:55 
 * @history:
 */
public interface ISYSMenuDAO extends IBaseDAO<SYSMenu> {
    String NAMESPACE = ISYSMenuDAO.class.getName().concat(".");

    public int update(SYSMenu data);
}
