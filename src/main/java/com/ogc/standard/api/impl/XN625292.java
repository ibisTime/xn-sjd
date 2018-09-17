package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625292Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十二月/05.
 */
public class XN625292 extends AProcessor {

    private IMarketAO marketAO = SpringContextHolder.getBean(IMarketAO.class);

    private XN625292Req req;

    @Override
    public Object doBusiness() throws BizException {

        return this.marketAO.coinPriceByPlatform(req.getCoin(),
            req.getRefCurrency());

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625292Req.class);
        ObjValidater.validateReq(req);

    }
}
