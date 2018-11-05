package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629401Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:31 AM 
 * @history:
 */
public class XN629401 extends AProcessor {
    private IPresellProductAO presellProductAO = SpringContextHolder
        .getBean(IPresellProductAO.class);

    private XN629401Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        presellProductAO.editPresellProduct(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629401Req.class);
        ObjValidater.validateReq(req);
    }
}
