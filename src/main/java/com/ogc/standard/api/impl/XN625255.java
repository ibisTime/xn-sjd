package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625255Req;
import com.ogc.standard.dto.res.XN625255Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/22.
 */
public class XN625255 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    private XN625255Req req;

    @Override
    public Object doBusiness() throws BizException {

        XN625255Res res = new XN625255Res();
        BigDecimal totalTradeCount = this.tradeOrderAO.getUserTotalTradeCount(
            req.getUserId(), req.getCurrency());
        res.setTotalTradeCount(totalTradeCount.toString());
        return res;
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625255Req.class);
        ObjValidater.validateReq(req);
    }
}
