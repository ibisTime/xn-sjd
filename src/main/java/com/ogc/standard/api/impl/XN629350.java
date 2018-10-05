package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629350Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 收取碳泡泡
 * @author: jiafr 
 * @since: 2018年10月2日 下午3:13:33 
 * @history:
 */
public class XN629350 extends AProcessor {
    private ICarbonBubbleOrderAO carbonBubbleOrderAO = SpringContextHolder
        .getBean(ICarbonBubbleOrderAO.class);

    private XN629350Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        carbonBubbleOrderAO.takeCarbonBubble(req.getCode(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629350Req.class);
        ObjValidater.validateReq(req);
    }

}
