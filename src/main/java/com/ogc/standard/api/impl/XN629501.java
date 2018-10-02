package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629501Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 下架道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629501 extends AProcessor {
    private IToolAO toolAO = SpringContextHolder.getBean(IToolAO.class);

    private XN629501Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        toolAO.putUp(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629501Req.class);
        ObjValidater.validateReq(req);
    }
}
