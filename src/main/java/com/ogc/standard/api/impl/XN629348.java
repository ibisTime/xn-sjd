package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629348Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 用户是否点赞收藏
 * @author: silver 
 * @since: Nov 23, 2018 5:10:22 PM 
 * @history:
 */
public class XN629348 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629348Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return articleAO.isPointCollect(req.getCode(), req.getUserId(),
            req.getType());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629348Req.class);
        ObjValidater.validateReq(req);
    }
}
