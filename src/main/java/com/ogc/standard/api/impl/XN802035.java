package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICurrencyRateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CurrencyRate;
import com.ogc.standard.dto.req.XN802035Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查汇率
 * @author: dl 
 * @since: 2018年8月27日 下午10:34:13 
 * @history:
 */
public class XN802035 extends AProcessor {

    private ICurrencyRateAO currencyRateAO = SpringContextHolder
        .getBean(ICurrencyRateAO.class);

    private XN802035Req req;

    @Override
    public Object doBusiness() throws BizException {

        CurrencyRate condition = new CurrencyRate();
        condition.setCurrency(req.getCurrency());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return this.currencyRateAO.queryPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN802035Req.class);
        ObjValidater.validateReq(req);

    }
}
