package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629606Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询申请绑定养护方记录
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:19:19 
 * @history:
 */
public class XN629606 extends AProcessor {
    private IApplyBindMaintainAO applyBindMaintainAO = SpringContextHolder
        .getBean(IApplyBindMaintainAO.class);

    private XN629606Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return applyBindMaintainAO.getApplyBindMaintain(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629606Req.class);
        ObjValidater.validateReq(req);
    }
}
