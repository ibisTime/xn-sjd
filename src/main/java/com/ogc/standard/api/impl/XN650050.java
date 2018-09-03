package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISimuOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650050Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 【模拟交易】委托下单
 * @author: lei 
 * @since: 2018年8月22日 下午2:04:15 
 * @history:
 */
public class XN650050 extends AProcessor {

    private ISimuOrderAO simuOrderAO = SpringContextHolder
        .getBean(ISimuOrderAO.class);

    private XN650050Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(simuOrderAO.submit(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650050Req.class);
        ObjValidater.validateReq(req);
    }
}
