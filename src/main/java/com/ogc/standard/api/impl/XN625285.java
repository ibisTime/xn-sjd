package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICoinAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CoinAcceptOrder;
import com.ogc.standard.dto.req.XN625285Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 平台收款处理
 * @author: lei 
 * @since: 2018年9月10日 下午10:21:20 
 * @history:
 */
public class XN625285 extends AProcessor {

    private ICoinAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(ICoinAcceptOrderAO.class);

    private XN625285Req req;

    @Override
    public Object doBusiness() throws BizException {

        CoinAcceptOrder condition = new CoinAcceptOrder();
        condition.setUserId(req.getBuyUser());
        condition.setPayCardNo(req.getPayCardNo());
        condition.setPayType(req.getPayType());
        condition.setReceiveCardNo(req.getReceiveCardNo());
        condition.setReceiveType(req.getReceiveType());

        condition.setAcceptUser(req.getSellUser());
        condition.setStatus(req.getStatus());
        condition.setTradeCoin(req.getTradeCoin());
        condition.setTradeCurrency(req.getTradeCurrency());
        condition.setType(req.getType());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICoinAcceptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return coinAcceptOrderAO.queryCoinAcceptOrderPage(start, limit,
            condition);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625285Req.class);
        ObjValidater.validateReq(req);
    }

}
