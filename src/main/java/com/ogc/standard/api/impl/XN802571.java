/**
 * @Title XN802570.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午3:29:29 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBtcMAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802571Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 弃用散取地址
 * @author: taojian 
 * @since: 2018年9月11日 下午3:29:29 
 * @history:
 */
public class XN802571 extends AProcessor {

    private IBtcMAddressAO btcMAddressAO = SpringContextHolder
        .getBean(IBtcMAddressAO.class);

    private XN802571Req req;

    @Override
    public Object doBusiness() throws BizException {
        btcMAddressAO.abandon(StringValidater.toLong(req.getId()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802571Req.class);
        ObjValidater.validateReq(req);
    }

}
