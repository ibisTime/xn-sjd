package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.KeyWord;

/**
 * 关键字
 * @author: silver 
 * @since: 2018年8月22日 上午10:33:46 
 * @history:
 */
public interface IKeyWordDAO extends IBaseDAO<KeyWord> {
    String NAMESPACE = IKeyWordDAO.class.getName().concat(".");

    // 修改关键字
    public int updateKeyWord(KeyWord data);

}
