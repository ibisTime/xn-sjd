package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.req.XN629049Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 我的订单分页查询
 * @author: xieyj 
 * @since: 2018年10月3日 下午10:20:26 
 * @history:
 */
public class XN629049 extends AProcessor {

    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private XN629049Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrder condition = new AdoptOrder();
        condition.setType(req.getType());
        condition.setApplyUser(req.getUserId());
        condition.setStatus(req.getStatus());
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
        req = JsonUtil.json2Bean(inputparams, XN629049Req.class);
        req.setUserId(operator);
        ObjValidater.validateReq(req);
    }
}
