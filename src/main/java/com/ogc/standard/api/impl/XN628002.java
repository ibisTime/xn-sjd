package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN628002Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:01:34 
 * @history:
 */
public class XN628002 extends AProcessor {
    private IKeywordAO keyWordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN628002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keyWordAO.editKeyword(StringValidater.toInteger(req.getId()),
            req.getWord(), req.getLevel(), req.getReaction(), req.getRemark(),
            req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628002Req.class);
        ObjValidater.validateReq(req);
    }

}
