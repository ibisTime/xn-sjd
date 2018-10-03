package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630060Req;
import com.ogc.standard.dto.res.PKUserRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 注册用户
 * @author: jiafr 
 * @since: 2018年9月28日 下午8:16:33 
 * @history:
 */
public class XN630060 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630060Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKUserRes(userAO.registerSYSUserOwner(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630060Req.class);
        ObjValidater.validateReq(req);
    }
}
