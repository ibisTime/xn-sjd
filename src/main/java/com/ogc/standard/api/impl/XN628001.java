package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN628001Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 删除关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:01:34 
 * @history:
 */
public class XN628001 extends AProcessor {
    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN628001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.dropKeyword(StringValidater.toInteger(req.getId()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628001Req.class);
        ObjValidater.validateReq(req);
    }

}
