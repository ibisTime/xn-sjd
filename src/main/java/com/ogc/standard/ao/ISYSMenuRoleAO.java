package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.domain.SYSMenuRole;

/**
 * @author: Gejin 
 * @since: 2016年4月16日 下午10:37:44 
 * @history:
 */
public interface ISYSMenuRoleAO {

    public int addSYSMenuRole(SYSMenuRole data);

    public List<SYSMenu> querySYSMenuList(SYSMenuRole data);

}
