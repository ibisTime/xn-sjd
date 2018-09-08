package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625291Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 
 * @author: lei 
 * @since: 2018年9月8日 下午10:20:52 
 * @history:
 */
public class XN625291 extends AProcessor {

    private IMarketAO marketAO = SpringContextHolder.getBean(IMarketAO.class);

    private XN625291Req req;

    @Override
    public Object doBusiness() throws BizException {

        return this.marketAO.marketListByReq(req);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625291Req.class);
        ObjValidater.validateReq(req);

    }
}
