package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625274Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 平台收款处理
 * @author: lei 
 * @since: 2018年9月10日 下午10:21:20 
 * @history:
 */
public class XN625274 extends AProcessor {

    private IAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(IAcceptOrderAO.class);

    private XN625274Req req;

    @Override
    public Object doBusiness() throws BizException {

        coinAcceptOrderAO.release(req.getCode(), req.getResult(),
            req.getUserId());
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625274Req.class);
        ObjValidater.validateReq(req);
    }

}
