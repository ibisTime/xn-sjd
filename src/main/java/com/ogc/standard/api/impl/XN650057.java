package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ISimuOrderDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.SimuOrderDetail;
import com.ogc.standard.dto.req.XN650057Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 【模拟交易】分页查询成交明细（front）
 * @author: lei 
 * @since: 2018年8月22日 下午3:36:20 
 * @history:
 */
public class XN650057 extends AProcessor {

    private ISimuOrderDetailAO simuOrderDetailAO = SpringContextHolder
        .getBean(ISimuOrderDetailAO.class);

    private XN650057Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        SimuOrderDetail condition = new SimuOrderDetail();
        condition.setOrderCode(req.getOrderCode());
        condition.setUserId(req.getUserId());
        condition.setSymbol(req.getSymbol());
        condition.setToSymbol(req.getToSymbol());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISimuOrderDetailAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, false);

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return simuOrderDetailAO.querySimuOrderDetailPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650057Req.class);
        ObjValidater.validateReq(req);
    }

}
