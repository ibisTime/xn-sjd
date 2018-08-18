package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630050Req;
import com.ogc.standard.dto.res.XN627312Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增用户
 * @author: nyc 
 * @since: 2018年4月26日 下午6:14:01 
 * @history:
 */

public class XN630050 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN627312Res(userAO.addSYSUser(req.getMobile(),
            req.getLoginPwd(), req.getRealName(), req.getPhoto()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630050Req.class);
        ObjValidater.validateReq(req);
    }
}
