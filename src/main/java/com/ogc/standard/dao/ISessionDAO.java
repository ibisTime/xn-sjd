/**
 * @Title ISessionDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午12:56:57 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Session;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午12:56:57 
 * @history:
 */
public interface ISessionDAO extends IBaseDAO<Session> {
    String NAMESPACE = ISessionDAO.class.getName().concat(".");

    public int updateUnreadSum1(Session data);

    public int updateUnreadSum2(Session data);

    public int updateClearUser1UnreadSum(Session data);

    public int updateClearUser2UnreadSum(Session data);

}
