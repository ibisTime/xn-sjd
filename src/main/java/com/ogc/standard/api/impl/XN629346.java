package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629346Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:47:13 
 * @history:
 */
public class XN629346 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629346Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return articleAO.getArticle(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629346Req.class);
        ObjValidater.validateReq(req);
    }
}
