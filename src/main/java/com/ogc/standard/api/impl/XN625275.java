/**
 * @Title XN625275.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月15日 下午6:57:10 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625275Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 承兑商付款处理
 * @author: taojian 
 * @since: 2018年9月15日 下午6:57:10 
 * @history:
 */
public class XN625275 extends AProcessor {

    private IAcceptOrderAO acceptOrderAO = SpringContextHolder
        .getBean(IAcceptOrderAO.class);

    private XN625275Req req;

    @Override
    public Object doBusiness() throws BizException {
        acceptOrderAO.platPay(req.getCode(), req.getResult(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625275Req.class);
        ObjValidater.validateReq(req);
    }

}
