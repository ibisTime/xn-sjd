package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.dto.req.XN629325Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 下午2:01:54 
 * @history:
 */
public class XN629325 extends AProcessor {
    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629325Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GiftOrder condition = new GiftOrder();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setStatus(req.getStatus());
        condition.setClaimer(req.getClaimer());
        condition.setToUser(req.getToUser());
        condition.setClaimDatetimeStart(DateUtil.strToDate(
            req.getClaimDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setClaimDatetimeEnd(DateUtil.strToDate(
            req.getClaimDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGiftOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return giftOrderAO.queryGiftOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629325Req.class);
        ObjValidater.validateReq(req);
    }
}
