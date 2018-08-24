package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IExchangePairAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.dto.req.XN650100Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

public class XN650100 extends AProcessor {
    private IExchangePairAO exchangePairAO = SpringContextHolder
        .getBean(IExchangePairAO.class);

    private XN650100Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ExchangePair condition = new ExchangePair();
        condition.setSymbol(req.getSymbol());
        condition.setToSymbol(req.getTosymbol());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IExchangePairAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, "asc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return exchangePairAO.queryExchangePairPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String userId)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650100Req.class);
        ObjValidater.validateReq(req);
    }

}
