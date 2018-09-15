/**
 * @Title IAwardDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午4:46:52 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Award;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午4:46:52 
 * @history:
 */
public interface IAwardDAO extends IBaseDAO<Award> {

    String NAMESPACE = IAwardDAO.class.getName().concat(".");

    public int updateStauts(Award data);
}
