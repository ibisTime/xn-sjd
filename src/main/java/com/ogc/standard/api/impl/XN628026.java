package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITeamMemberApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628026Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 报名申请详情查(front/oss)
 * @author: silver 
 * @since: 2018年8月21日 下午7:32:24 
 * @history:
 */
public class XN628026 extends AProcessor {

    private ITeamMemberApplyAO teamMemberApplyAO = SpringContextHolder
        .getBean(ITeamMemberApplyAO.class);

    private XN628026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return teamMemberApplyAO.getTeamMemberApply(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628026Req.class);
        ObjValidater.validateReq(req);
    }
}
