package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.AcceptOrder;
import com.ogc.standard.dto.req.XN625287Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询我的承兑订单
 * @author: lei 
 * @since: 2018年9月10日 下午10:21:20 
 * @history:
 */
public class XN625287 extends AProcessor {

    private IAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(IAcceptOrderAO.class);

    private XN625287Req req;

    @Override
    public Object doBusiness() throws BizException {

        AcceptOrder condition = new AcceptOrder();
        condition.setStatus(req.getStatus());
        condition.setTradeCoin(req.getTradeCoin());
        condition.setTradeCurrency(req.getTradeCurrency());
        condition.setType(req.getType());
        condition.setUserId(req.getUserId());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IAcceptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return coinAcceptOrderAO.queryAcceptOrderPage(start, limit, condition);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625287Req.class);
        ObjValidater.validateReq(req);
    }

}
