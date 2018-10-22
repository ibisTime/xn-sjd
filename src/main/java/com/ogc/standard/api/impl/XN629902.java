package com.ogc.standard.api.impl;

import java.util.Date;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629902Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 代理商佣金
 * @author: silver 
 * @since: Oct 22, 2018 11:16:36 AM 
 * @history:
 */
public class XN629902 extends AProcessor {
    private ISettleAO settleAO = SpringContextHolder.getBean(ISettleAO.class);

    private XN629902Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Date createStartDatetime = DateUtil.strToDate(
            req.getCreateStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING);
        Date createEndDatetime = DateUtil.strToDate(req.getCreateEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING);

        return settleAO.getSettleTotalAmount(req.getUserId(), req.getStatus(),
            createStartDatetime, createEndDatetime);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629902Req.class);
        ObjValidater.validateReq(req);
    }
}
