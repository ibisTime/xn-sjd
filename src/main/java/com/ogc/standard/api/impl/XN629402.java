package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629402Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 提交预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:12:31 AM 
 * @history:
 */
public class XN629402 extends AProcessor {
    private IPresellProductAO presellProductAO = SpringContextHolder
        .getBean(IPresellProductAO.class);

    private XN629402Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        presellProductAO.submitPresellProduct(req.getCode(), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629402Req.class);
        ObjValidater.validateReq(req);
    }
}
