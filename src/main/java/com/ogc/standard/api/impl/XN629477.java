package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGroupOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.GroupOrder;
import com.ogc.standard.dto.req.XN629477Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询寄售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:29:23 PM 
 * @history:
 */
public class XN629477 extends AProcessor {
    private IGroupOrderAO groupOrderAO = SpringContextHolder
        .getBean(IGroupOrderAO.class);

    private XN629477Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GroupOrder condition = new GroupOrder();
        condition.setProductCode(req.getProductCode());
        condition.setApplyUser(req.getUserId());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGroupOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return groupOrderAO.queryGroupOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629477Req.class);
        ObjValidater.validateReq(req);
    }

}
