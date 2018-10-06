package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN730083Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改支付密码
 * @author: silver 
 * @since: Oct 6, 2018 12:05:27 PM 
 * @history:
 */
public class XN730083 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730083Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentUserAO.doModifyTradePwd(req.getUserId(), req.getNewTradePwd(),
            req.getOldTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730083Req.class);
        ObjValidater.validateReq(req);
    }
}
