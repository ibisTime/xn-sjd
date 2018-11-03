package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629420Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 下单预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:17:38 PM 
 * @history:
 */
public class XN629420 extends AProcessor {
    private IPresellOrderAO presellOrderAO = SpringContextHolder
        .getBean(IPresellOrderAO.class);

    private XN629420Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Integer quantity = StringValidater.toInteger(req.getQuantity());
        String code = presellOrderAO.commitPresellOrder(req.getUserId(),
            req.getSpecsCode(), quantity);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629420Req.class);
        ObjValidater.validateReq(req);
    }

}
