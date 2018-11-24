package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629322Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 发货
 * @author: silver 
 * @since: Nov 24, 2018 5:31:36 PM 
 * @history:
 */
public class XN629322 extends AProcessor {
    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629322Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        giftOrderAO.sendGift(req.getCode());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629322Req.class);
        ObjValidater.validateReq(req);
    }
}
