package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IChargeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802343Req;
import com.ogc.standard.dto.res.PayOrderRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 线上充值申请
 * @author: myb858 
 * @since: 2017年5月3日 上午9:23:51 
 * @history:
 */
public class XN802343 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802343Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public synchronized Object doBusiness() throws BizException {
        BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
        String signOrder = chargeAO.applyOrderOnline(req.getUserId(),
            req.getPayType(), amount);
        return new PayOrderRes(signOrder);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802343Req.class);
        ObjValidater.validateReq(req);
    }
}
