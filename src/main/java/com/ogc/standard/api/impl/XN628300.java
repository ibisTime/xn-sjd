package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628300Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午2:45:21 
 * @history:
 */
public class XN628300 extends AProcessor {
    private IMatchApplyAO matchApplyAO = SpringContextHolder
        .getBean(IMatchApplyAO.class);

    private XN628300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(matchApplyAO.matchApply(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628300Req.class);
        ObjValidater.validateReq(req);
    }

}
