/**
 * @Title ICompanyDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author tao  
 * @date 2018年8月17日 下午4:42:57 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Company;

/** 
 * @author: tao
 * @since: 2018年8月17日 下午4:42:57 
 * @history:
 */
public interface ICompanyDAO extends IBaseDAO<Company> {
    String NAMESPACE = ICompanyDAO.class.getName().concat(".");

    public int update(Company data);

}
