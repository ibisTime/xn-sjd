package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ISimuOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.SimuOrder;
import com.ogc.standard.dto.req.XN650058Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 【模拟交易】我的委托单分页查询
 * @author: lei 
 * @since: 2018年8月22日 下午3:43:05 
 * @history:
 */
public class XN650058 extends AProcessor {

    private ISimuOrderAO simuOrderAO = SpringContextHolder
        .getBean(ISimuOrderAO.class);

    private XN650058Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        SimuOrder condition = new SimuOrder();
        condition.setUserId(req.getUserId());
        condition.setExchange(req.getExchange());
        condition.setSymbol(req.getSymbol());
        condition.setToSymbol(req.getToSymbol());
        condition.setType(req.getType());
        condition.setDirection(req.getDirection());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISimuOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, false);

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return simuOrderAO.querySimuOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650058Req.class);
        ObjValidater.validateReq(req);
    }

}
