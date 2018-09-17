package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IHandicapAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650064Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 交易深度
 * @author: lei 
 * @since: 2018年8月29日 下午3:51:38 
 * @history:
 */
public class XN650064 extends AProcessor {

    private IHandicapAO handicapAO = SpringContextHolder
        .getBean(IHandicapAO.class);

    private XN650064Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return handicapAO.getMarketDepth(req.getSymbol(), req.getToSymbol());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650064Req.class);
        ObjValidater.validateReq(req);
    }

}
