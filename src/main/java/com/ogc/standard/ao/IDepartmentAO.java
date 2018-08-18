/**
 * @Title IdepartmenAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午9:39:03 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Department;
import com.ogc.standard.dto.req.XN630310Req;
import com.ogc.standard.dto.req.XN630312Req;

/** 
 * @author: tao 
 * @since: 2018年8月18日 上午9:39:03 
 * @history:
 */
public interface IDepartmentAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public String addDepartment(XN630310Req req);

    public void dropDepartment(String code);

    public void editDepartment(XN630312Req req);

    public Paginable<Department> queryDepartmentPage(int start, int limit,
            Department condition);

    public List<Department> queryDepartmentList(Department condition);

    public Department getDepartment(String code);
}
