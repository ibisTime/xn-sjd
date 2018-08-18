package com.ogc.standard.api.impl;

import java.util.Date;

import com.ogc.standard.ao.ISYSMenuAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.SYSMenu;
import com.ogc.standard.dto.req.XN630012Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 菜单-修改
 * @author: xieyj 
 * @since: 2016年5月16日 下午11:00:43 
 * @history:
 */
public class XN630012 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN630012Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSMenu data = new SYSMenu();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setUrl(req.getUrl());
        data.setParentCode(req.getParentCode());
        data.setType(req.getType());
        data.setOrderNo(req.getOrderNo());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setSystemCode(req.getCode());
        return new BooleanRes(sysMenuAO.editSYSMenu(data));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630012Req.class);
        ObjValidater.validateReq(req);
    }
}
