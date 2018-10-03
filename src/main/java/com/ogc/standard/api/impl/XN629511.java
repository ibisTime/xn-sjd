package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629511Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 使用道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629511 extends AProcessor {
    private IToolOrderAO toolOrderAO = SpringContextHolder
        .getBean(IToolOrderAO.class);

    private XN629511Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        toolOrderAO.useTool(req.getToolOrderCode(), req.getAdoptTreeCode(),
            req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629511Req.class);
        // req.setUserId(operator);
        ObjValidater.validateReq(req);
    }
}
