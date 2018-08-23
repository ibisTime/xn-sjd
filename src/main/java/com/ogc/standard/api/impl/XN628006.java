package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN628006Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:10:37 
 * @history:
 */
public class XN628006 extends AProcessor {

    private IKeywordAO keyWordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN628006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return keyWordAO.getKeyword(StringValidater.toInteger(req.getId()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628006Req.class);
        ObjValidater.validateReq(req);
    }
}
