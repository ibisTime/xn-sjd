/**
 * @Title IDepartmentDAO.java 
 * @Package com.ogc.standard.dao 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午8:54:33 
 * @version V1.0   
 */
package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Department;

/** 
 * @author: tao 
 * @since: 2018年8月17日 下午8:54:33 
 * @history:
 */
public interface IDepartmentDAO extends IBaseDAO<Department> {
    String NAMESPACE = IDepartmentDAO.class.getName().concat(".");

    public int update(Department data);

}
