package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629472Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 支付转增订单
 * @author: silver 
 * @since: Nov 12, 2018 7:31:26 PM 
 * @history:
 */
public class XN629472 extends AProcessor {
    private IGroupOrderAO groupOrderAO = SpringContextHolder
        .getBean(IGroupOrderAO.class);

    private XN629472Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupOrderAO.toPayDonateGroupOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629472Req.class);
        ObjValidater.validateReq(req);
    }

}
