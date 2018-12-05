package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDefaultPostageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629796Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:50:53 PM 
 * @history:
 */
public class XN629796 extends AProcessor {

    private IDefaultPostageAO defaultPostageAO = SpringContextHolder
        .getBean(IDefaultPostageAO.class);

    private XN629796Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return defaultPostageAO.getDefaultPostage(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629796Req.class);
        ObjValidater.validateReq(req);
    }
}
