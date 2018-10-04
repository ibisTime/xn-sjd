package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.req.XN629045Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询个人捐赠专属认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:02:36 
 * @history:
 */
public class XN629045 extends AProcessor {

    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private XN629045Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrder condition = new AdoptOrder();
        condition.setType(req.getType());
        condition.setProductCode(req.getProductCode());
        condition.setProductSpecsName(req.getProductSpecsName());
        condition.setApplyUser(req.getUserId());
        condition.setStatus(req.getStatus());
        condition.setSettleStatus(req.getSettleStatus());
        condition.setStartDatetimeStart(DateUtil.getFrontDate(
            req.getStartDatetimeStart(), false));
        condition.setStartDatetimeEnd(DateUtil.getFrontDate(
            req.getStartDatetimeEnd(), true));
        condition.setEndDatetimeStart(DateUtil.getFrontDate(
            req.getEndDatetimeStart(), false));
        condition.setEndDatetimeEnd(DateUtil.getFrontDate(
            req.getEndDatetimeEnd(), true));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAdoptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return adoptOrderAO.queryAdoptOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629045Req.class);
        ObjValidater.validateReq(req);
    }
}
