package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN730076Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改头像
 * @author: silver 
 * @since: 2018年9月28日 下午3:48:19 
 * @history:
 */
public class XN730076 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730076Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentUserAO.modifyPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730076Req.class);
        ObjValidater.validateReq(req);
    }
}
