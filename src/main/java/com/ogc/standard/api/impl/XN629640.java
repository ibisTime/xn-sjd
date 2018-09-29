package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629640Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 审核结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:40:42 PM 
 * @history:
 */
public class XN629640 extends AProcessor {
    private ISettleAO settleAO = SpringContextHolder.getBean(ISettleAO.class);

    private XN629640Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        settleAO.approveSettleByRef(req.getRefCode(), req.getApproveResult(),
            req.getHandleNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629640Req.class);
        ObjValidater.validateReq(req);
    }
}
