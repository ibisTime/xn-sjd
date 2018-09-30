package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiveCarbonBubbleRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629366Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 30, 2018 11:20:40 AM 
 * @history:
 */
public class XN629366 extends AProcessor {

    private IGiveCarbonBubbleRecordAO giveCarbonBubbleRecordAO = SpringContextHolder
        .getBean(IGiveCarbonBubbleRecordAO.class);

    private XN629366Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return giveCarbonBubbleRecordAO
            .getGiveCarbonBubbleRecord(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629366Req.class);
        ObjValidater.validateReq(req);
    }
}
