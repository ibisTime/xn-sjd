package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Keyword;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:33:46 
 * @history:
 */
public interface IKeywordDAO extends IBaseDAO<Keyword> {
    String NAMESPACE = IKeywordDAO.class.getName().concat(".");

    // 修改关键字
    public int updateKeyword(Keyword data);

}
