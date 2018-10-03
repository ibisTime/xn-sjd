package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630061Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 提交资料
 * @author: xieyj 
 * @since: 2018年10月4日 上午2:52:36 
 * @history:
 */
public class XN630061 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN630061Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysUserAO.commitCompany(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630061Req.class);
        ObjValidater.validateReq(req);

    }

}
