package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.PresellOrder;
import com.ogc.standard.dto.req.XN629425Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:29:23 PM 
 * @history:
 */
public class XN629425 extends AProcessor {
    private IPresellOrderAO presellOrderAO = SpringContextHolder
        .getBean(IPresellOrderAO.class);

    private XN629425Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PresellOrder condition = new PresellOrder();
        condition.setProductCode(req.getProductCode());
        condition.setApplyUser(req.getUserId());
        condition.setStatus(req.getStatus());
        condition.setSettleStatus(req.getSettleStatus());
        condition.setExistsSettle(req.getExistsSettle());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IPresellOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return presellOrderAO.queryPresellOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629425Req.class);
        ObjValidater.validateReq(req);
    }

}
