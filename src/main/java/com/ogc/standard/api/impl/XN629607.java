package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629607Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:26:05 
 * @history:
 */
public class XN629607 extends AProcessor {
    private IApplyBindMaintainAO applyBindMaintainAO = SpringContextHolder
        .getBean(IApplyBindMaintainAO.class);

    private XN629607Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setOwnerId(req.getOwnerId());
        condition.setMaintainId(req.getMaintainId());
        condition.setStatus(req.getStatus());
        return applyBindMaintainAO.queryApplyBindMaintainList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629607Req.class);
        ObjValidater.validateReq(req);
    }
}
