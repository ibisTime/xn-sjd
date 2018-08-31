/**
 * @Title XN802376.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午4:51:18 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDepositAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802376Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查询定存订单
 * @author: dl 
 * @since: 2018年8月31日 下午4:51:18 
 * @history:
 */
public class XN802376 extends AProcessor {

    private IDepositAO depositAO = SpringContextHolder
        .getBean(IDepositAO.class);

    private XN802376Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return depositAO.getDeposit(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802376Req.class);
        ObjValidater.validateReq(req);
    }

}
