package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGiveCarbonBubbleRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.GiveCarbonBubbleRecord;
import com.ogc.standard.dto.req.XN629367Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 30, 2018 11:20:40 AM 
 * @history:
 */
public class XN629367 extends AProcessor {

    private IGiveCarbonBubbleRecordAO giveCarbonBubbleRecordAO = SpringContextHolder
        .getBean(IGiveCarbonBubbleRecordAO.class);

    private XN629367Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GiveCarbonBubbleRecord condition = new GiveCarbonBubbleRecord();
        condition.setUserId(req.getUserId());
        condition.setToUserId(req.getToUserId());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGiveCarbonBubbleRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return giveCarbonBubbleRecordAO
            .queryGiveCarbonBubbleRecordList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629367Req.class);
        ObjValidater.validateReq(req);
    }
}
