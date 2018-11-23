package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629349Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 用户收藏的文章
 * @author: silver 
 * @since: Nov 23, 2018 7:33:18 PM 
 * @history:
 */
public class XN629349 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629349Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return articleAO.queryMyCollectArticleList(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629349Req.class);
        ObjValidater.validateReq(req);
    }
}
