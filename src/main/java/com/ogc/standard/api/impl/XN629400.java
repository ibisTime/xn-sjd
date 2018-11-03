package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629400Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:13 AM 
 * @history:
 */
public class XN629400 extends AProcessor {
    private IPresellProductAO presellProductAO = SpringContextHolder
        .getBean(IPresellProductAO.class);

    private XN629400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(presellProductAO.addPresellProduct(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629400Req.class);
        ObjValidater.validateReq(req);
    }
}
