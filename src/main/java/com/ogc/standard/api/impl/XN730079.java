package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN730079Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 自助修改登录密码
 * @author: silver 
 * @since: 2018年9月28日 下午3:48:19 
 * @history:
 */
public class XN730079 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730079Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        agentUserAO.doResetLoginPwd(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730079Req.class);
        ObjValidater.validateReq(req);
    }
}
