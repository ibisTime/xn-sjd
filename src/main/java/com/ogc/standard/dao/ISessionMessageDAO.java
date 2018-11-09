/**
 * @Title ISessionMessageDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午1:04:15 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SessionMessage;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 下午1:04:15 
 * @history:
 */
public interface ISessionMessageDAO extends IBaseDAO<SessionMessage> {
    String NAMESPACE = ISessionMessageDAO.class.getName().concat(".");

    public int updateStatus(SessionMessage data);
}
