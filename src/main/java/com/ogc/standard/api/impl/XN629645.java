package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ISettleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.dto.req.XN629645Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629645 extends AProcessor {
    private ISettleAO settleAO = SpringContextHolder.getBean(ISettleAO.class);

    private XN629645Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Settle condition = new Settle();
        condition.setUserId(req.getUserId());
        condition.setUserKind(req.getUserKind());
        condition.setRefType(req.getRefType());
        condition.setRefCode(req.getRefCode());
        condition.setStatus(req.getStatus());

        condition.setCreateStartDatetime(DateUtil.strToDate(
            req.getCreateStartDatetime(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateEndDatetime(DateUtil.strToDate(
            req.getCreateEndDatetime(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISettleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return settleAO.querySettlePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629645Req.class);
        ObjValidater.validateReq(req);
    }
}
