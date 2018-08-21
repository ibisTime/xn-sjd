package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805067Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 重置支付密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:26:47 
 * @history:
 */
public class XN805067 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805067Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetTradePwd(req.getUserId(), req.getNewTradePwd(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805067Req.class);
        ObjValidater.validateReq(req);
    }
}
