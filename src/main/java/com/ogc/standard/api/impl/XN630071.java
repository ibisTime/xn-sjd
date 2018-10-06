package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630071Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改支付密码
 * @author: silver 
 * @since: Oct 6, 2018 11:32:23 AM 
 * @history:
 */
public class XN630071 extends AProcessor {

    private ISYSUserAO sIsysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630071Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sIsysUserAO.doModifyTradePwd(req.getUserId(), req.getNewTradePwd(),
            req.getOldTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630071Req.class);
        ObjValidater.validateReq(req);
    }
}
