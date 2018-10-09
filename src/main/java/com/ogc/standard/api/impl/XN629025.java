package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Product;
import com.ogc.standard.dto.req.XN629025Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询认养产品
 * @author: silver 
 * @since: 2018年9月27日 上午10:52:14 
 * @history:
 */
public class XN629025 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629025Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Product condition = new Product();
        condition.setName(req.getName());
        condition.setCode(req.getCode());
        condition.setParentCategoryCode(req.getParentCategoryCode());
        condition.setCategoryCode(req.getCategoryCode());
        condition.setOwnerId(req.getOwnerId());

        condition.setSellType(req.getSellType());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());

        condition.setRaiseStartStartDatetime(DateUtil.strToDate(
            req.getRaiseStartStartDatetime(), DateUtil.DB_DATE_FORMAT_STRING));
        condition.setRaiseStartEndDatetime(DateUtil.strToDate(
            req.getRaiseStartEndDatetime(), DateUtil.DB_DATE_FORMAT_STRING));
        condition.setRaiseEndStartDatetime(DateUtil.strToDate(
            req.getRaiseEndStartDatetime(), DateUtil.DB_DATE_FORMAT_STRING));
        condition.setRaiseEndEndDatetime(DateUtil.strToDate(
            req.getRaiseEndEndDatetime(), DateUtil.DB_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProductAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return productAO.queryProductPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629025Req.class);
        ObjValidater.validateReq(req);
    }
}
