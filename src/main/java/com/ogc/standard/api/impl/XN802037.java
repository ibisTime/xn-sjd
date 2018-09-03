package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICurrencyRateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802037Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 币种查汇率
 * @author: dl 
 * @since: 2018年8月27日 下午10:47:46 
 * @history:
 */
public class XN802037 extends AProcessor {

    private ICurrencyRateAO ethAddressAO = SpringContextHolder
        .getBean(ICurrencyRateAO.class);

    private XN802037Req req;

    @Override
    public Object doBusiness() throws BizException {
        return this.ethAddressAO.currencyRateByCurrency(req.getCurrency());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN802037Req.class);
        ObjValidater.validateReq(req);

    }
}
