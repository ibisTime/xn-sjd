package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629416Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:51:14 AM 
 * @history:
 */
public class XN629416 extends AProcessor {
    private IPresellProductAO presellProductAO = SpringContextHolder
        .getBean(IPresellProductAO.class);

    private XN629416Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return presellProductAO.getPresellProduct(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629416Req.class);
        ObjValidater.validateReq(req);
    }
}
