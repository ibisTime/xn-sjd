package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN730081Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增业务员
 * @author: silver 
 * @since: 2018年9月28日 下午3:48:19 
 * @history:
 */
public class XN730081 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730081Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(
            agentUserAO.doAddSalesman(req.getMobile(), req.getLoginPwd()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730081Req.class);
        ObjValidater.validateReq(req);
    }
}
