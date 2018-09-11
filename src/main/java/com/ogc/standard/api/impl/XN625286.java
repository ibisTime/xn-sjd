package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICoinAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625286Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 平台收款处理
 * @author: lei 
 * @since: 2018年9月10日 下午10:21:20 
 * @history:
 */
public class XN625286 extends AProcessor {

    private ICoinAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(ICoinAcceptOrderAO.class);

    private XN625286Req req;

    @Override
    public Object doBusiness() throws BizException {

        return coinAcceptOrderAO.getCoinAcceptOrder(req.getCode());

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625286Req.class);
        ObjValidater.validateReq(req);
    }

}
