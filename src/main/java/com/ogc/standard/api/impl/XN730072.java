package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN730072Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 代注册分销商
 * @author: xieyj 
 * @since: 2018年10月4日 下午8:29:17 
 * @history:
 */
public class XN730072 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730072Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String code = agentUserAO.doAddAgent(req);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730072Req.class);
        ObjValidater.validateReq(req);
    }
}
