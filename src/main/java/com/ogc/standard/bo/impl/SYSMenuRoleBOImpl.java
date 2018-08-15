/**
 * @Title SYSMenuRoleBOImpl.java 
 * @Package com.std.security.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年4月17日 上午8:58:08 
 * @version V1.0   
 */
package com.ogc.standard.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISYSMenuRoleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.dao.ISYSMenuRoleDAO;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.domain.SYSMenuRole;

/** 
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:58:08 
 * @history:
 */
@Component
public class SYSMenuRoleBOImpl extends PaginableBOImpl<SYSMenuRole> implements
        ISYSMenuRoleBO {

    @Autowired
    private ISYSMenuRoleDAO sysMenuRoleDAO;

    @Override
    public int saveSYSMenuRole(SYSMenuRole data) {
        int count = 0;
        if (data != null) {
            data.setUpdateDatetime(new Date());
            count = sysMenuRoleDAO.insert(data);
        }
        return count;
    }

    @Override
    public int removeSYSMenuList(String roleCode) {
        int count = 0;
        if (StringUtils.isNotBlank(roleCode)) {
            SYSMenuRole condition = new SYSMenuRole();
            condition.setRoleCode(roleCode);
            count = sysMenuRoleDAO.deleteSYSMenuList(condition);
        }
        return count;
    }

    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenuRole data) {
        return sysMenuRoleDAO.selectSYSMenuList(data);
    }
}
