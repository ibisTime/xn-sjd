package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629032Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 点赞/取消点赞
 * @author: silver 
 * @since: Sep 30, 2018 10:50:15 AM 
 * @history:
 */
public class XN629032 extends AProcessor {
    private ITreeAO treeAO = SpringContextHolder.getBean(ITreeAO.class);

    private XN629032Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        treeAO.pointTree(req.getUserId(), req.getTreeNumber());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629032Req.class);
        ObjValidater.validateReq(req);
    }
}
