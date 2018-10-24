package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.dto.req.XN629055Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午11:03:27 
 * @history:
 */
public class XN629055 extends AProcessor {

    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629055Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setProductCode(req.getProductCode());
        condition.setProductSpecsCode(req.getProductSpecsCode());
        condition.setStatus(req.getStatus());
        condition.setStartDatetimeStart(DateUtil.strToDate(
            req.getStartDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setStartDatetimeStart(DateUtil.strToDate(
            req.getStartDatetimeEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setEndDatetimeStart(DateUtil.strToDate(
            req.getEndDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setEndDatetimeStart(DateUtil.strToDate(
            req.getEndDatetimeEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGroupAdoptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return groupAdoptOrderAO.queryGroupAdoptOrderPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629055Req.class);
        ObjValidater.validateReq(req);
    }
}
