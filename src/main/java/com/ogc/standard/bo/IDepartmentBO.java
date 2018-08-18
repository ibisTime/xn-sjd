/**
 * @Title IDepartmentBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午9:05:58 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Department;

/** 
 * @author: tao 
 * @since: 2018年8月17日 下午9:05:58 
 * @history:
 */
public interface IDepartmentBO extends IPaginableBO<Department> {

    public String saveDepartment(Department data);

    public int removeDepartment(String code);

    public int refreshDepartment(Department data);

    public List<Department> queryDepartmentList(Department data);

    public Department getDepartment(String code);

    public boolean isDepartmentExist(String code);
}
