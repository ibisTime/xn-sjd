package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805080Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改用户头像
 * @author: xieyj 
 * @since: 2017年7月16日 下午3:01:12 
 * @history:
 */
public class XN805080 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805080Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.modifyPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805080Req.class);
        ObjValidater.validateReq(req);
    }
}
