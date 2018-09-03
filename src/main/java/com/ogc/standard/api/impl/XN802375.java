/**
 * @Title XN802375.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午4:43:43 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDepositAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Deposit;
import com.ogc.standard.dto.req.XN802375Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查定存订单
 * @author: dl 
 * @since: 2018年8月31日 下午4:43:43 
 * @history:
 */
public class XN802375 extends AProcessor {

    private IDepositAO depositAO = SpringContextHolder
        .getBean(IDepositAO.class);

    private XN802375Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Deposit condition = new Deposit();
        condition.setCodeForQuery(req.getCode());
        condition.setFromAddress(req.getFromAddress());
        condition.setToAddress(req.getToAddress());
        condition.setTxHash(req.getTxHash());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return depositAO.queryDepositPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802375Req.class);
        ObjValidater.validateReq(req);
    }

}
