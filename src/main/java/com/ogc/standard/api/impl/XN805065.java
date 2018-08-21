package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805065Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * admin管理员重置密码
 * @author: xieyj 
 * @since: 2017年7月16日 下午2:19:27 
 * @history:
 */
public class XN805065 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805065Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetLoginPwdByOss(req.getUserId(), req.getLoginPwd(),
            req.getAdminUserId(), req.getAdminPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805065Req.class);
        ObjValidater.validateReq(req);
    }
}
