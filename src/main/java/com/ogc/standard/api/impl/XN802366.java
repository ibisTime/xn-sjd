/**
 * @Title XN802366.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午1:45:34 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICollectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802366Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查归集订单
 * @author: dl 
 * @since: 2018年8月31日 下午1:45:34 
 * @history:
 */
public class XN802366 extends AProcessor {

    private ICollectAO collectAO = SpringContextHolder
        .getBean(ICollectAO.class);

    private XN802366Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return collectAO.getCollect(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802366Req.class);
        ObjValidater.validateReq(req);
    }

}
