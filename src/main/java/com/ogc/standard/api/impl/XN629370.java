package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IShareRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629370Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分享
 * @author: silver 
 * @since: Sep 30, 2018 12:33:06 PM 
 * @history:
 */
public class XN629370 extends AProcessor {
    private IShareRecordAO shareRecordAO = SpringContextHolder
        .getBean(IShareRecordAO.class);

    private XN629370Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(shareRecordAO.addShareRecord(req.getUserId(),
            req.getChannel(), req.getContent()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629370Req.class);
        ObjValidater.validateReq(req);
    }
}
