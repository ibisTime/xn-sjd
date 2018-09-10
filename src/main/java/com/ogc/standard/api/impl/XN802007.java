package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICoinAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Coin;
import com.ogc.standard.dto.req.XN802007Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:12:25 
 * @history:
 */
public class XN802007 extends AProcessor {

    private ICoinAO coinAO = SpringContextHolder.getBean(ICoinAO.class);

    private XN802007Req req = null;

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
        return coinAO.queryCoinList(condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802007Req.class);
        ObjValidater.validateReq(req);
    }

}
