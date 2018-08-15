package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.domain.SYSMenuRole;

/**
 * 角色菜单
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:24:53 
 * @history:
 */
public interface ISYSMenuRoleDAO extends IBaseDAO<SYSMenuRole> {
    String NAMESPACE = ISYSMenuRoleDAO.class.getName().concat(".");

    public List<SYSMenu> selectSYSMenuList(SYSMenuRole data);

    public int deleteSYSMenuList(SYSMenuRole data);
}
