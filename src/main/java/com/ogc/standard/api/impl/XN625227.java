package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Ads;
import com.ogc.standard.dto.req.XN625227Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public class XN625227 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    private XN625227Req req;

    @Override
    public Object doBusiness() throws BizException {

        Ads condition = new Ads();
        condition.setTradeCoin(req.getCoin());
        condition.setTradeType(req.getTradeType());
        condition.setPayType(req.getPayType());
        condition.setTradeCoin(req.getCoin());
        condition.setMinPrice(StringValidater.toBigDecimal(req.getMaxPrice()));
        condition.setMinPrice(StringValidater.toBigDecimal(req.getMinPrice()));
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return this.adsAO.frontPage(start, limit, condition);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625227Req.class);
        ObjValidater.validateReq(req);
    }
}
