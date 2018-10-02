package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629510Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午10:03:27 
 * @history:
 */
public class XN629510 extends AProcessor {
    private IToolOrderAO toolOrderAO = SpringContextHolder
        .getBean(IToolOrderAO.class);

    private XN629510Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(
            toolOrderAO.addToolOrder(req.getToolCode(), req.getUserId()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629510Req.class);
        ObjValidater.validateReq(req);
    }
}
