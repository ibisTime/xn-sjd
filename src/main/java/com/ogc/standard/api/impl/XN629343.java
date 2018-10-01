package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629343Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 上架文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:10:26 
 * @history:
 */
public class XN629343 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629343Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        articleAO.putOn(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629343Req.class);
        ObjValidater.validateReq(req);
    }
}
