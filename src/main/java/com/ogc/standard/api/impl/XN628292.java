package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628292Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增赛事
 * @author: silver 
 * @since: 2018年8月21日 上午11:40:47 
 * @history:
 */
public class XN628292 extends AProcessor {
    private IMatchAO matchAO = SpringContextHolder.getBean(IMatchAO.class);

    private XN628292Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        matchAO.publishMatch(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628292Req.class);
        ObjValidater.validateReq(req);
    }

}
