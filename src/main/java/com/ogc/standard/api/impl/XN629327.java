package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.GiftOrder;
import com.ogc.standard.dto.req.XN629327Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询礼物订单
 * @author: jiafr 
 * @since: 2018年10月1日 上午9:42:40 
 * @history:
 */
public class XN629327 extends AProcessor {

    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629327Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GiftOrder condition = new GiftOrder();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setStatus(req.getStatus());
        condition.setClaimer(req.getClaimer());
        condition.setClaimDatetimeStart(DateUtil.strToDate(
            req.getClaimDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setClaimDatetimeEnd(DateUtil.strToDate(
            req.getClaimDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGiveTreeRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return giftOrderAO.queryGiftOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629327Req.class);
        ObjValidater.validateReq(req);
    }
}
