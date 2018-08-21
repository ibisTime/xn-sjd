package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628301Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 参赛审核
 * @author: silver 
 * @since: 2018年8月21日 下午3:14:43 
 * @history:
 */
public class XN628301 extends AProcessor {
    private IMatchApplyAO matchApplyAO = SpringContextHolder
        .getBean(IMatchApplyAO.class);

    private XN628301Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        matchApplyAO.approveMatchApply(req.getCode(), req.getApproveResult(),
            req.getApprover(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628301Req.class);
        ObjValidater.validateReq(req);
    }

}
