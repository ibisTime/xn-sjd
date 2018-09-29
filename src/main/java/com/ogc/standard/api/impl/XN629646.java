package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629646Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629646 extends AProcessor {
    private ISettleAO settleAO = SpringContextHolder.getBean(ISettleAO.class);

    private XN629646Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return settleAO.getSettle(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629646Req.class);
        ObjValidater.validateReq(req);
    }
}
