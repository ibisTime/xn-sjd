package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802353Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 提现支付回录
 * @author: xieyj 
 * @since: 2018年10月5日 下午9:00:50 
 * @history:
 */
public class XN802353 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802353Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802353.class) {
            for (String code : req.getCodeList()) {
                BigDecimal transFee = new BigDecimal(req.getTransFee());
                withdrawAO.payOrder(code, req.getPayUser(), req.getPayResult(),
                    req.getPayNote(), req.getChannelOrder(), transFee);
            }
            return new BooleanRes(true);
        }
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802353Req.class);
        ObjValidater.validateReq(req);
    }

}
