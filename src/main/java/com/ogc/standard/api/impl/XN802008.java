package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICoinAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802008Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:12:25 
 * @history:
 */
public class XN802008 extends AProcessor {

    private ICoinAO coinAO = SpringContextHolder.getBean(ICoinAO.class);

    private XN802008Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return coinAO.getCoin(req.getSymbol().toUpperCase());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802008Req.class);
        ObjValidater.validateReq(req);
    }

}
