package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBarrageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629384Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 发送弹幕
 * @author: silver 
 * @since: Nov 23, 2018 1:49:46 PM 
 * @history:
 */
public class XN629384 extends AProcessor {

    private IBarrageAO barrageAO = SpringContextHolder
        .getBean(IBarrageAO.class);

    private XN629384Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        barrageAO.sendBarrage(req.getAdoptTreeCode(), req.getUserId(),
            req.getCode());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629384Req.class);
        ObjValidater.validateReq(req);
    }
}
