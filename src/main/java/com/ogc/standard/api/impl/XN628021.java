package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITeamMemberApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628021Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 加入战队申请(front)
 * @author: silver 
 * @since: 2018年8月21日 下午7:24:17 
 * @history:
 */
public class XN628021 extends AProcessor {
    private ITeamMemberApplyAO teamMemberApplyAO = SpringContextHolder
        .getBean(ITeamMemberApplyAO.class);

    private XN628021Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        teamMemberApplyAO.approveTeamMemberApply(req.getCode(),
            req.getApproveResult(), req.getApprover(), req.getRemark());
        return new Boolean(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628021Req.class);
        ObjValidater.validateReq(req);
    }

}
