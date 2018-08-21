package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628296Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查赛事
 * @author: silver 
 * @since: 2018年8月21日 下午12:16:53 
 * @history:
 */
public class XN628296 extends AProcessor {
    private IMatchAO matchAO = SpringContextHolder.getBean(IMatchAO.class);

    private XN628296Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return matchAO.getMatch(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628296Req.class);
        ObjValidater.validateReq(req);
    }
}
