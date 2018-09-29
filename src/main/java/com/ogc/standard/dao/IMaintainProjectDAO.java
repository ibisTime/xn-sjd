package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.MaintainProject;

/**
 * 养护项目
 * @author: silver 
 * @since: 2018年9月28日 下午8:11:46 
 * @history:
 */
public interface IMaintainProjectDAO extends IBaseDAO<MaintainProject> {
    String NAMESPACE = IMaintainProjectDAO.class.getName().concat(".");

    // 修改养护项目
    public int updateMaintainProject(MaintainProject data);

}
