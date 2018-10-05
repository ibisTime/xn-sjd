package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629204Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 留言
 * @author: silver 
 * @since: Oct 5, 2018 1:41:16 PM 
 * @history:
 */
public class XN629204 extends AProcessor {
    private IBizLogAO bizLogAO = SpringContextHolder.getBean(IBizLogAO.class);

    private XN629204Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(String.valueOf(bizLogAO.leaveMessage(
            req.getAdoptTreeCode(), req.getNote(), req.getUserId())));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629204Req.class);
        ObjValidater.validateReq(req);
    }
}
