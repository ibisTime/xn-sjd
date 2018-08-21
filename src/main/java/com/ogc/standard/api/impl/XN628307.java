package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IMatchApplyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.MatchApply;
import com.ogc.standard.dto.req.XN628307Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 我的报名分页查询
 * @author: silver 
 * @since: 2018年8月21日 下午3:08:03 
 * @history:
 */
public class XN628307 extends AProcessor {

    private IMatchApplyAO matchApplyAO = SpringContextHolder
        .getBean(IMatchApplyAO.class);

    private XN628307Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        MatchApply condition = new MatchApply();
        condition.setApplyUser(req.getUserId());

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
        req = JsonUtil.json2Bean(inputparams, XN628307Req.class);
        ObjValidater.validateReq(req);
    }
}
