package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IVisitorAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.dto.req.XN629315Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询来访人
 * @author: jiafr 
 * @since: 2018年9月30日 上午10:22:36 
 * @history:
 */
public class XN629315 extends AProcessor {

    private IVisitorAO visitorAO = SpringContextHolder
        .getBean(IVisitorAO.class);

    private XN629315Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Visitor condition = new Visitor();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setUserId(req.getUserId());
        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(
            req.getCreateDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IVisitorAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return visitorAO.queryVisitorPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629315Req.class);
        ObjValidater.validateReq(req);
    }
}
