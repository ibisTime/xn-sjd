/**
 * @Title IDepositDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午2:54:59 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Deposit;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午2:54:59 
 * @history:
 */
public interface IDepositDAO extends IBaseDAO<Deposit> {

    String NAMESPACE = IDepositDAO.class.getName().concat(".");
}
