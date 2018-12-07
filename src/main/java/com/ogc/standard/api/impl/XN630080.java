package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630080Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 产权方修改关于我们
 * @author: silver 
 * @since: Dec 7, 2018 12:25:47 PM 
 * @history:
 */
public class XN630080 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        companyAO.refreshCompanyOwner(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630080Req.class);
        ObjValidater.validateReq(req);
    }
}
