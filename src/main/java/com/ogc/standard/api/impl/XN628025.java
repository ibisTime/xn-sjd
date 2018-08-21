package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ITeamMemberApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.TeamMemberApply;
import com.ogc.standard.dto.req.XN628025Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 报名申请分页查询(front/oss)
 * @author: silver 
 * @since: 2018年8月21日 下午7:32:24 
 * @history:
 */
public class XN628025 extends AProcessor {

    private ITeamMemberApplyAO teamMemberApplyAO = SpringContextHolder
        .getBean(ITeamMemberApplyAO.class);

    private XN628025Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        TeamMemberApply condition = new TeamMemberApply();
        condition.setApplyUser(req.getApplyUser());
        condition.setStatus(req.getStatus());
        condition.setTeamCode(req.getTeamCode());

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
            column = ITeamMemberApplyAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return teamMemberApplyAO.queryTeamMemberApplyPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628025Req.class);
        ObjValidater.validateReq(req);
    }
}
