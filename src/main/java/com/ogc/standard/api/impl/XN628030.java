package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPostAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN628030Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 发帖(front)
 * @author: silver 
 * @since: 2018年8月22日 下午3:55:46 
 * @history:
 */
public class XN628030 extends AProcessor {
    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN628030Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return postAO.publishPost(req.getUserId(), req.getContent(),
            req.getPlateCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628030Req.class);
        ObjValidater.validateReq(req);
    }

}
