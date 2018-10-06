package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802354Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 取现回录
 * @author: xieyj 
 * @since: 2018年10月6日 下午1:32:35 
 * @history:
 */
public class XN802354 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802354Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802354.class) {
            BigDecimal amount = new BigDecimal(req.getAmount());
            withdrawAO.withdrawEnter(req.getAccountNumber(), amount,
                req.getWithDate(), req.getChannelOrder(), req.getWithNote(),
                req.getUpdater());
            return new BooleanRes(true);
        }
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802354Req.class);
        ObjValidater.validateReq(req);
    }

}
