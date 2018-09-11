package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICoinAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625271Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 出售X币
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:09 
 * @history:
 */
public class XN625271 extends AProcessor {

    private ICoinAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(ICoinAcceptOrderAO.class);

    private XN625271Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(coinAcceptOrderAO.sellOrder(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625271Req.class);
        ObjValidater.validateReq(req);

    }

}
