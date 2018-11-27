package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629812Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 阅读文章
 * @author: silver 
 * @since: Nov 23, 2018 1:16:03 PM 
 * @history:
 */
public class XN629812 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629812Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        articleAO.readArticle(req.getCode(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629812Req.class);
        ObjValidater.validateReq(req);
    }
}
