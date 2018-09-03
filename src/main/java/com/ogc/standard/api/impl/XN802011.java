/**
 * @Title XN802170.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:08:46 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPersonalAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802011Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:08:46 
 * @history:
 */
public class XN802011 extends AProcessor {
    private IPersonalAddressAO personalAddressAO = SpringContextHolder
        .getBean(IPersonalAddressAO.class);

    private XN802011Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        personalAddressAO
            .dropPersonalAddress(StringValidater.toLong(req.getId()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802011Req.class);
        ObjValidater.validateReq(req);
    }

}
