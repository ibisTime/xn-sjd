package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630072Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 重置支付密码
 * @author: silver 
 * @since: Oct 6, 2018 11:32:23 AM 
 * @history:
 */
public class XN630072 extends AProcessor {

    private ISYSUserAO sIsysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630072Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sIsysUserAO.resetTradePwd(req.getUserId(), req.getNewTradePwd(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630072Req.class);
        ObjValidater.validateReq(req);
    }
}
