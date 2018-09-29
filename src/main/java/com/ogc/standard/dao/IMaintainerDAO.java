package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Maintainer;

/**
 * 养护人
 * @author: silver 
 * @since: 2018年9月28日 下午7:37:39 
 * @history:
 */
public interface IMaintainerDAO extends IBaseDAO<Maintainer> {
    String NAMESPACE = IMaintainerDAO.class.getName().concat(".");

    public int updateMaintainer(Maintainer data);

}
