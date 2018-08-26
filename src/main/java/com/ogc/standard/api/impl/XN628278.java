package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628278Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 评论详情查(front)
 * @author: xieyj 
 * @since: 2018年8月26日 下午2:53:34 
 * @history:
 */
public class XN628278 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN628278Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return commentAO.getFrontComment(req.getCode(), req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628278Req.class);
        ObjValidater.validateReq(req);
    }
}
