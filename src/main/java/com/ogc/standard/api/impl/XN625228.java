package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Ads;
import com.ogc.standard.dto.req.XN625228Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public class XN625228 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder
            .getBean(IAdsAO.class);

    XN625228Req req;

    @Override
    public Object doBusiness() throws BizException {

        Ads condition = new Ads();
        condition.setTradeCoin(req.getCoin());
        condition.setTradeType(req.getTradeType());
        condition.setPayType(req.getPayType());
        condition.setMaxPrice(req.getMaxPrice());
        condition.setMinPrice(req.getMinPrice());
        return this.adsAO.frontPage(req.getStart(),req.getLimit(),condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625228Req.class);
        ObjValidater.validateReq(req);
    }
}
