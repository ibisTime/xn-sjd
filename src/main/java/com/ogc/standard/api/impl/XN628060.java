package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPostAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628060Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询帖子(front)
 * @author: silver 
 * @since: 2018年8月22日 下午4:17:49 
 * @history:
 */
public class XN628060 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN628060Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postAO.getFrontPost(req.getCode(), req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628060Req.class);
        ObjValidater.validateReq(req);
    }
}