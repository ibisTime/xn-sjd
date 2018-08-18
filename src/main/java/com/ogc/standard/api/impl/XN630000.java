package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSRoleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630000Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 角色-新增
 * @author: xieyj 
 * @since: 2016年4月17日 上午8:25:15 
 * @history:
 */
public class XN630000 extends AProcessor {
    private ISYSRoleAO sysRoleAO = SpringContextHolder
        .getBean(ISYSRoleAO.class);

    private XN630000Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(sysRoleAO.addSYSRole(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630000Req.class);
        ObjValidater.validateReq(req);
    }

}
