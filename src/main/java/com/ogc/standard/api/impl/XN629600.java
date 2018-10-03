package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629600Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午9:49:16 
 * @history:
 */
public class XN629600 extends AProcessor {
    private IApplyBindMaintainAO applyBindMaintainAO = SpringContextHolder
        .getBean(IApplyBindMaintainAO.class);

    private XN629600Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(applyBindMaintainAO.applyBindMaintain(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629600Req.class);
        ObjValidater.validateReq(req);
    }

}
