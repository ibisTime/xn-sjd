package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802350Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 线下取现申请
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN802350 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802350Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        synchronized (XN802350.class) {
            BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
            String code = withdrawAO.applyOrder(req.getAccountNumber(), amount,
                req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser(),
                req.getApplyNote());
            return new PKCodeRes(code);
        }

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802350Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
