package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITeamAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628016Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询战队(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午5:33:17 
 * @history:
 */
public class XN628016 extends AProcessor {

    private ITeamAO teamAO = SpringContextHolder.getBean(ITeamAO.class);

    private XN628016Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return teamAO.getTeam(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628016Req.class);
        ObjValidater.validateReq(req);
    }
}