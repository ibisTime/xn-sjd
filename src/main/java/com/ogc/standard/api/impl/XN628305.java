package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.dto.req.XN628305Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午3:08:03 
 * @history:
 */
public class XN628305 extends AProcessor {

    private IMatchApplyAO matchApplyAO = SpringContextHolder
        .getBean(IMatchApplyAO.class);

    private XN628305Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        MatchApply condition = new MatchApply();
        condition.setApplyUser(req.getApplyUser());
        condition.setApprover(req.getApprover());
        condition.setStatus(req.getStatus());

        condition.setApplyStartDatetime(DateUtil.strToDate(
            req.getApplyStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setApplyEndDatetime(DateUtil.strToDate(
            req.getApplyEndDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setApproveStartDatetime(DateUtil.strToDate(
            req.getApproveStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setApproveEndDatetime(DateUtil.strToDate(
            req.getApproveEndDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMatchApplyAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return matchApplyAO.queryMatchApplyPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628305Req.class);
        ObjValidater.validateReq(req);
    }
}
