package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628306Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午3:15:11 
 * @history:
 */
public class XN628306 extends AProcessor {

    private IMatchApplyAO matchApplyAO = SpringContextHolder
        .getBean(IMatchApplyAO.class);

    private XN628306Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return matchApplyAO.getMatchApply(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628306Req.class);
        ObjValidater.validateReq(req);
    }
}
