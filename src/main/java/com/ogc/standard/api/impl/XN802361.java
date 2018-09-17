package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICollectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802361Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * eth token手动归集
 * @author: kongliang 
 * @since: 2018年9月3日 下午1:58:33 
 * @history:
 */
public class XN802361 extends AProcessor {

    private ICollectAO collectAO = SpringContextHolder
        .getBean(ICollectAO.class);

    private XN802361Req req = null;

    /** 
     * @seecom.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        collectAO.ethTokenCollect(req.getBalanceStart(), req.getSymbol(), null);
        return new BooleanRes(true);
    }

    /**  
     * @seecom.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802361Req.class);
        ObjValidater.validateReq(req);
    }

}
