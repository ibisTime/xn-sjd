package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IGiveCarbonBubbleRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629360Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 赠送碳泡泡
 * @author: silver 
 * @since: Sep 30, 2018 11:08:03 AM 
 * @history:
 */
public class XN629360 extends AProcessor {
    private IGiveCarbonBubbleRecordAO giveCarbonBubbleRecordAO = SpringContextHolder
        .getBean(IGiveCarbonBubbleRecordAO.class);

    private XN629360Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BigDecimal quantity = new BigDecimal(req.getQuantity());
        return new PKCodeRes(giveCarbonBubbleRecordAO.addGiveCarbonBubbleRecord(
            req.getUserId(), req.getToUserId(), quantity));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629360Req.class);
        ObjValidater.validateReq(req);
    }
}
