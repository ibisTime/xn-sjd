package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPersonalAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802010Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:08:46 
 * @history:
 */
public class XN802010 extends AProcessor {
    private IPersonalAddressAO personalAddressAO = SpringContextHolder
        .getBean(IPersonalAddressAO.class);

    private XN802010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        personalAddressAO.addPersonalAddress(req.getSymbol(), req.getAddress(),
            req.getLabel(), req.getUserId(), req.getIsCerti());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802010Req.class);
        ObjValidater.validateReq(req);
    }

}
