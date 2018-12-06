package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IApplyBindMaintainAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.ApplyBindMaintain;
import com.ogc.standard.dto.req.XN629605Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:11:13 
 * @history:
 */
public class XN629605 extends AProcessor {

    private IApplyBindMaintainAO applyBindMaintainAO = SpringContextHolder
        .getBean(IApplyBindMaintainAO.class);

    private XN629605Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ApplyBindMaintain condition = new ApplyBindMaintain();
        condition.setOwnerId(req.getOwnerId());
        condition.setMaintainId(req.getMaintainId());
        condition.setStatus(req.getStatus());
        condition.setOwnerUserName(req.getOwnerUserName());
        condition.setMaintainUserName(req.getMaintainUserName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IApplyBindMaintainAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return applyBindMaintainAO.queryApplyBindMaintainPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629605Req.class);
        ObjValidater.validateReq(req);
    }
}
