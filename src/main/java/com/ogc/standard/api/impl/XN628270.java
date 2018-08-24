package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628270Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 审核评论(oss)
 * @author: silver 
 * @since: 2018年8月23日 上午11:29:09 
 * @history:
 */
public class XN628270 extends AProcessor {
    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN628270Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.approveComment(req.getCode(), req.getApproveResult(),
            req.getApprover(), req.getApproveNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628270Req.class);
        ObjValidater.validateReq(req);
    }

}