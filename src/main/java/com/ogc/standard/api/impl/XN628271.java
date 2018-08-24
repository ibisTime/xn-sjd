package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628271Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 评论评论(front)
 * @author: silver 
 * @since: 2018年8月23日 上午11:29:09 
 * @history:
 */
public class XN628271 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN628271Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.commentComment(req.getCode(), req.getContent(), req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628271Req.class);
        ObjValidater.validateReq(req);
    }

}
