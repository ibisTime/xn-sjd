package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN805062Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改手机号(需支付密码)
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:27 
 * @history:
 */
public class XN805062 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805062Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doChangeMoblie(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), req.getTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805062Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), req.getTradePwd());
    }
}
