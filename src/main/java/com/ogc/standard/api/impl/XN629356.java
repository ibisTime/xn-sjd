package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629356Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询碳泡泡产生订单
 * @author: jiafr 
 * @since: 2018年10月2日 下午2:16:02 
 * @history:
 */
public class XN629356 extends AProcessor {

    private ICarbonBubbleOrderAO carbonBubbleOrderAO = SpringContextHolder
        .getBean(ICarbonBubbleOrderAO.class);

    private XN629356Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return carbonBubbleOrderAO.getCarbonBubbleOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629356Req.class);
        ObjValidater.validateReq(req);
    }
}
