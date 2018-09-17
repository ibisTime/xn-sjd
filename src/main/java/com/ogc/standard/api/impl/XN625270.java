package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625270Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 购买X币
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:09 
 * @history:
 */
public class XN625270 extends AProcessor {

    private IAcceptOrderAO acceptOrderAO = SpringContextHolder
        .getBean(IAcceptOrderAO.class);

    private XN625270Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String code = acceptOrderAO.buyOrder(req);
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625270Req.class);
        ObjValidater.validateReq(req);

    }

}
