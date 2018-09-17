/**
 * @Title IUserIdAuthDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午5:06:13 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.UserIdAuth;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 下午5:06:13 
 * @history:
 */
public interface IUserIdAuthDAO extends IBaseDAO<UserIdAuth> {
    String NAMESPACE = IUserIdAuthDAO.class.getName().concat(".");

    public int updateresult(UserIdAuth data);
}
