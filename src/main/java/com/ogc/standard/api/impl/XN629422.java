package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629422Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 支付预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:22:08 PM 
 * @history:
 */
public class XN629422 extends AProcessor {
    private IPresellOrderAO presellOrderAO = SpringContextHolder
        .getBean(IPresellOrderAO.class);

    private XN629422Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        presellOrderAO.toPayPresellOrder(req.getCode(), req.getPayType(),
            req.getTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629422Req.class);
        ObjValidater.validateReq(req);
    }

}