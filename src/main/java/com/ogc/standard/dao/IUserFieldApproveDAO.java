/**
 * @Title IUserEditApplyDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 上午11:26:48 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.UserFieldApprove;

/** 
 * @author: taojian 
 * @since: 2018年9月13日 上午11:26:48 
 * @history:
 */
public interface IUserFieldApproveDAO extends IBaseDAO<UserFieldApprove> {
    String NAMESPACE = IUserFieldApproveDAO.class.getName().concat(".");

    public int updateResult(UserFieldApprove data);
}
