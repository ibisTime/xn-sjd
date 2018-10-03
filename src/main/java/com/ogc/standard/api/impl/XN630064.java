package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630064Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 代申请用户
 * @author: jiafr 
 * @since: 2018年9月28日 下午6:13:19 
 * @history:
 */
public class XN630064 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630064Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(sysUserAO.proxyApplySYSUser(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630064Req.class);
        ObjValidater.validateReq(req);
    }
}
