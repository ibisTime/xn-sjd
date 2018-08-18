package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.ISYSMenuAO;
import com.ogc.standard.bo.ISYSMenuBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.dto.req.XN630010Req;
import com.ogc.standard.exception.BizException;

@Service
public class SYSMenuAOImpl implements ISYSMenuAO {

    @Autowired
    ISYSMenuBO sysMenuBO;

    @Override
    public String addSYSMenu(XN630010Req req) {
        SYSMenu data = new SYSMenu();
        if (req != null) {
            // 判断父编号是否存在

            data.setName(req.getName());
            data.setType(req.getType());
            data.setUrl(req.getUrl());
            data.setParentCode(req.getParentCode());
            data.setOrderNo(req.getOrderNo());

            data.setUpdater(req.getUpdater());
            data.setRemark(req.getRemark());
            data.setSystemCode(req.getSystemCode());
            if (!"0".equalsIgnoreCase(data.getParentCode())
                    && !sysMenuBO.isSYSMenuExist(data.getParentCode())) {
                throw new BizException("lh0000", "父节点菜单编号不存在！");
            }
            sysMenuBO.saveSYSMenu(data);
        } else {
            throw new BizException("lh0000", "菜单编号已经存在！");
        }
        return data.getCode();
    }

    @Override
    public boolean dropSYSMenu(String code) {
        if (!sysMenuBO.isSYSMenuExist((code))) {
            throw new BizException("lh0000", "删除菜单不存在！");
        }
        sysMenuBO.removeSYSMenu(code);
        return true;
    }

    @Override
    public boolean editSYSMenu(SYSMenu data) {
        if (data != null && sysMenuBO.isSYSMenuExist(data.getCode())) {
            sysMenuBO.refreshSYSMenu(data);
        } else {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        return true;
    }

    @Override
    public Paginable<SYSMenu> querySYSMenuPage(int start, int limit,
            SYSMenu condition) {
        return sysMenuBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SYSMenu> querySYSMenuList(SYSMenu condition) {
        return sysMenuBO.querySYSMenuList(condition);
    }

    @Override
    public SYSMenu getSYSMenu(String code) {
        if (!sysMenuBO.isSYSMenuExist(code)) {
            throw new BizException("lh0000", "菜单编号不存在！");
        }
        SYSMenu condition = new SYSMenu();
        condition.setCode(code);
        return sysMenuBO.getSYSMenu(code);
    }
}
