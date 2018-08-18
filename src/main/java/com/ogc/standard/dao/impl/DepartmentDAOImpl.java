/**
 * @Title DepartmentDAOImpl.java 
 * @Package com.ogc.standard.dao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午8:59:07 
 * @version V1.0   
 */
package com.ogc.standard.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ogc.standard.dao.IDepartmentDAO;
import com.ogc.standard.dao.base.support.AMybatisTemplate;
import com.ogc.standard.domain.Department;

/** 
 * @author: dl 
 * @since: 2018年8月17日 下午8:59:07 
 * @history:
 */
@Repository("departmentDAOImpl")
public class DepartmentDAOImpl extends AMybatisTemplate
        implements IDepartmentDAO {

    @Override
    public int insert(Department data) {
        return super.insert(NAMESPACE.concat("insert_department"), data);
    }

    @Override
    public int delete(Department data) {
        return super.delete(NAMESPACE.concat("delete_department"), data);
    }

    @Override
    public Department select(Department condition) {
        return super.select(NAMESPACE.concat("select_department"), condition,
            Department.class);
    }

    @Override
    public long selectTotalCount(Department condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_department_count"), condition);
    }

    @Override
    public List<Department> selectList(Department condition) {
        return super.selectList(NAMESPACE.concat("select_department"),
            condition, Department.class);
    }

    @Override
    public List<Department> selectList(Department condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_department"), start,
            count, condition, Department.class);
    }

    @Override
    public int update(Department data) {
        return super.update(NAMESPACE.concat("update_department"), data);
    }

}
