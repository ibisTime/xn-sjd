/**
 * @Title XN802250.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:12:25 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICoinAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Coin;
import com.ogc.standard.dto.req.XN802005Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:12:25 
 * @history:
 */
public class XN802005 extends AProcessor {

    private ICoinAO coinAO = SpringContextHolder.getBean(ICoinAO.class);

    private XN802005Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Coin condition = new Coin();
        condition.setSymbolForQuery(req.getSymbol());
        condition.setEname(req.getEname());
        condition.setCname(req.getCname());
        condition.setType(req.getType());
        condition.setContractAddress(req.getContractAddress());
        condition.setStatus(req.getStatus());
        condition.setOrder(ICoinAO.DEFAULT_ORDER_COLUMN, "asc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return coinAO.queryCoinPage(start, limit, condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802005Req.class);
        ObjValidater.validateReq(req);
    }

}
