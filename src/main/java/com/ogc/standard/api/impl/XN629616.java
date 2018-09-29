package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMaintainerAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629616Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查询养护人
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629616 extends AProcessor {
    private IMaintainerAO maintainerAO = SpringContextHolder
        .getBean(IMaintainerAO.class);

    private XN629616Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return maintainerAO.getMaintainer(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629616Req.class);
        ObjValidater.validateReq(req);
    }
}
