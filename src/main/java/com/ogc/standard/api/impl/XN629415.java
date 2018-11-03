package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IPresellProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.PresellProduct;
import com.ogc.standard.dto.req.XN629415Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询预售产品
 * @author: silver 
 * @since: Nov 3, 2018 10:44:41 AM 
 * @history:
 */
public class XN629415 extends AProcessor {
    private IPresellProductAO presellProductAO = SpringContextHolder
        .getBean(IPresellProductAO.class);

    private XN629415Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PresellProduct condition = new PresellProduct();
        condition.setName(req.getName());
        condition.setCategoryCode(req.getCategoryCode());
        condition.setOwnerId(req.getOwnerId());

        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IPresellProductAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return presellProductAO.queryPresellProductPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629415Req.class);
        ObjValidater.validateReq(req);
    }
}
