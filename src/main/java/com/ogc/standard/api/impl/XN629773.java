/**
 * @Title XN629770.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 上午10:46:44 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAfterSaleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629773Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 处理售后
 * @author: taojian 
 * @since: 2018年11月8日 上午10:46:44 
 * @history:
 */
public class XN629773 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN629773Req req;

    @Override
    public Object doBusiness() throws BizException {
        afterSaleAO.doReceive(req.getCode(), req.getReceiver());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629773Req.class);
        ObjValidater.validateReq(req);
    }

}
