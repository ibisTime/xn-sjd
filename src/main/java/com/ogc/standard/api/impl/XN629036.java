package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629036Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询树
 * @author: silver 
 * @since: 2018年9月27日 上午10:52:14 
 * @history:
 */
public class XN629036 extends AProcessor {

    private ITreeAO treeAO = SpringContextHolder.getBean(ITreeAO.class);

    private XN629036Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return treeAO.getTree(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629036Req.class);
        ObjValidater.validateReq(req);
    }
}
