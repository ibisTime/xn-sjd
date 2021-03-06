package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629456Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询寄售
 * @author: silver 
 * @since: Nov 4, 2018 3:28:16 PM 
 * @history:
 */
public class XN629456 extends AProcessor {
    private IDeriveGroupAO deriveGroupAO = SpringContextHolder
        .getBean(IDeriveGroupAO.class);

    private XN629456Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return deriveGroupAO.getDeriveGroup(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629456Req.class);
        ObjValidater.validateReq(req);
    }

}
