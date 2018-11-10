package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629471Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 支付订单
 * @author: silver 
 * @since: Nov 6, 2018 6:57:52 PM 
 * @history:
 */
public class XN629471 extends AProcessor {
    private IGroupOrderAO groupOrderAO = SpringContextHolder
        .getBean(IGroupOrderAO.class);

    private XN629471Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupOrderAO.toPayGroupOrder(req.getCode(), req.getPayType(),
            req.getTradePwd());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629471Req.class);
        ObjValidater.validateReq(req);
    }

}
