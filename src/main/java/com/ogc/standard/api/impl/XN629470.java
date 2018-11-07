package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629470Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 取消订单
 * @author: silver 
 * @since: Nov 6, 2018 6:57:52 PM 
 * @history:
 */
public class XN629470 extends AProcessor {
    private IGroupOrderAO groupOrderAO = SpringContextHolder
        .getBean(IGroupOrderAO.class);

    private XN629470Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        groupOrderAO.cancelGroupOrder(req.getCode(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629470Req.class);
        ObjValidater.validateReq(req);
    }

}
