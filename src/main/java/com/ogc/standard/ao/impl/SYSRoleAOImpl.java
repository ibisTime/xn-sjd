package com.ogc.standard.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ISYSRoleAO;
import com.ogc.standard.bo.ISYSMenuRoleBO;
import com.ogc.standard.bo.ISYSRoleBO;
import com.ogc.standard.bo.ISYSUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSRole;
import com.ogc.standard.domain.SYSUser;
import com.ogc.standard.dto.req.XN630000Req;
import com.ogc.standard.dto.req.XN630002Req;
import com.ogc.standard.exception.BizException;

@Service
public class SYSRoleAOImpl implements ISYSRoleAO {

    @Autowired
    ISYSRoleBO sysRoleBO;

    @Autowired
    ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Override
    public String addSYSRole(XN630000Req req) {
        SYSRole data = new SYSRole();
        data.setName(req.getName());
        data.setType(req.getType());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        data.setSystemCode(req.getSystemCode());
        sysRoleBO.saveSYSRole(data);
        return data.getCode();
    }

    @Override
    @Transactional
    public boolean dropSYSRole(String roleCode) {
        if (!sysRoleBO.isSYSRoleExist(roleCode)) {
            throw new BizException("lh4000", "角色编号不存在！");
        }
        // User condition = new User();
        // condition.setRoleCode(roleCode);
        // List<User> list = userBO.queryUserList(condition);
        // if (!CollectionUtils.sizeIsEmpty(list)) {
        // throw new BizException("lh4000", "该角色已在使用，无法删除！");
        // }
        // 删除角色和角色菜单关联表
        sysRoleBO.removeSYSRole(roleCode);
        sysMenuRoleBO.removeSYSMenuList(roleCode);
        return true;
    }

    @Override
    public boolean editSYSRole(XN630002Req req) {
        if (req != null && sysRoleBO.isSYSRoleExist(req.getCode())) {
            SYSRole data = new SYSRole();
            data.setCode(req.getCode());
            data.setName(req.getName());
            data.setUpdater(req.getUpdater());
            data.setRemark(req.getRemark());
            sysRoleBO.refreshSYSRole(data);
        } else {
            throw new BizException("lh4000", "角色编号不存在！");
        }
        return true;
    }

    @Override
    public List<SYSRole> querySYSRoleList(SYSRole condition) {
        return sysRoleBO.querySYSRoleList(condition);
    }

    @Override
    public Paginable<SYSRole> querySYSRolePage(int start, int limit,
            SYSRole condition) {
        Paginable<SYSRole> page = sysRoleBO.getPaginable(start, limit,
            condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (SYSRole sysRole : page.getList()) {
                initRole(sysRole);
            }
        }

        return page;
    }

    /** 
     * @see com.std.user.ao.ISYSRoleAO#getSYSRole(java.lang.String)
     */
    @Override
    public SYSRole getSYSRole(String code) {
        SYSRole sysRole = sysRoleBO.getSYSRole(code);

        initRole(sysRole);

        return sysRole;
    }

    private void initRole(SYSRole sysRole) {
        SYSUser sysUser = sysUserBO.getSYSUserUnCheck(sysRole.getUpdater());
        if (null != sysUser) {
            sysRole.setUpdaterName(sysUser.getLoginName());
        }
    }
}
