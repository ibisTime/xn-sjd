package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IChargeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802342Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 积分池手动加积分
 * @author: xieyj 
 * @since: 2018年10月6日 下午2:07:59 
 * @history:
 */
public class XN802342 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802342Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802342.class) {
            BigDecimal amount = new BigDecimal(req.getAmount());
            chargeAO.addSysAccount(amount, req.getCurrency(), req.getBizNote(),
                req.getUpdater());
            return new BooleanRes(true);
        }
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802342Req.class);
        ObjValidater.validateReq(req);
    }
}
