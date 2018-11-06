package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629476Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询订单
 * @author: silver 
 * @since: Nov 3, 2018 5:29:23 PM 
 * @history:
 */
public class XN629476 extends AProcessor {
    private IGroupOrderAO groupOrderAO = SpringContextHolder
        .getBean(IGroupOrderAO.class);

    private XN629476Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupOrderAO.getGroupOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629476Req.class);
        ObjValidater.validateReq(req);
    }

}
