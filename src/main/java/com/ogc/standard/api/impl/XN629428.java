package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629428Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 已预售人员名单
 * @author: silver 
 * @since: Nov 3, 2018 5:29:23 PM 
 * @history:
 */
public class XN629428 extends AProcessor {
    private IPresellOrderAO presellOrderAO = SpringContextHolder
        .getBean(IPresellOrderAO.class);

    private XN629428Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return presellOrderAO
            .queryPresellOrderListByProduct(req.getProductCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629428Req.class);
        ObjValidater.validateReq(req);
    }

}
