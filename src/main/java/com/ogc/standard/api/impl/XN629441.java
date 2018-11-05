package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629441Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 认领定向寄售
 * @author: silver 
 * @since: Nov 4, 2018 2:57:06 PM 
 * @history:
 */
public class XN629441 extends AProcessor {
    private IDeriveGroupAO deriveGroupAO = SpringContextHolder
        .getBean(IDeriveGroupAO.class);

    private XN629441Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        deriveGroupAO.claimDirect(req.getCode(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629441Req.class);
        ObjValidater.validateReq(req);
    }

}
