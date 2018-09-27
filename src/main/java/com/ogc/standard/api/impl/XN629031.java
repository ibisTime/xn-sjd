package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629031Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改古树
 * @author: silver 
 * @since: 2018年9月27日 下午8:32:54 
 * @history:
 */
public class XN629031 extends AProcessor {
    private ITreeAO treeAO = SpringContextHolder.getBean(ITreeAO.class);

    private XN629031Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        treeAO.editTree(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629031Req.class);
        ObjValidater.validateReq(req);
    }
}
