package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805051Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 微信登录(强绑手机号)
 * @author: xieyj 
 * @since: 2018年11月26日 上午10:57:02 
 * @history:
 */
public class XN805051 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.doLoginWeChat(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805051Req.class);
        ObjValidater.validateReq(req);
    }
}
