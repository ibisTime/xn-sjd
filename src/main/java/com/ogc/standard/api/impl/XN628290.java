package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628290Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增赛事
 * @author: silver 
 * @since: 2018年8月21日 上午11:40:47 
 * @history:
 */
public class XN628290 extends AProcessor {
    private IMatchAO matchAO = SpringContextHolder.getBean(IMatchAO.class);

    private XN628290Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(matchAO.addMatch(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628290Req.class);
        ObjValidater.validateReq(req);
    }

}
