package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IDefaultPostageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.DefaultPostage;
import com.ogc.standard.dto.req.XN629797Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:50:53 PM 
 * @history:
 */
public class XN629797 extends AProcessor {

    private IDefaultPostageAO defaultPostageAO = SpringContextHolder
        .getBean(IDefaultPostageAO.class);

    private XN629797Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        DefaultPostage condition = new DefaultPostage();
        condition.setShopCode(req.getShopCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IDefaultPostageAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return defaultPostageAO.queryDefaultPostageList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629797Req.class);
        ObjValidater.validateReq(req);
    }
}
