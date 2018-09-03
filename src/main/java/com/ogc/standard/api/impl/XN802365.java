/**
 * @Title XN802365.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午1:01:33 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICollectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Collect;
import com.ogc.standard.dto.req.XN802365Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查归集订单
 * @author: dl 
 * @since: 2018年8月31日 下午1:01:33 
 * @history:
 */
public class XN802365 extends AProcessor {

    private ICollectAO collectAO = SpringContextHolder
        .getBean(ICollectAO.class);

    private XN802365Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Collect condition = new Collect();
        condition.setCurrency(req.getCurrency());
        condition.setCodeForQuery(req.getCode());
        condition.setFromAddress(req.getFromAddress());
        condition.setToAddress(req.getToAddress());
        condition.setStatus(req.getStatus());
        condition.setTxHash(req.getTxHash());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return collectAO.queryCollectPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802365Req.class);
        ObjValidater.validateReq(req);
    }

}
