package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.dto.req.XN629725Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查订单
 * @author: taojian 
 * @since: 2018年11月7日 上午10:34:19 
 * @history:
 */
public class XN629725 extends AProcessor {

    private ICommodityOrderAO commodityOrderAO = SpringContextHolder
        .getBean(ICommodityOrderAO.class);

    private XN629725Req req;

    @Override
    public Object doBusiness() throws BizException {
        CommodityOrder condition = new CommodityOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setExpressType(req.getExpressType());
        condition.setPayType(req.getPayType());
        condition.setStatus(req.getStatus());
        condition.setCode(req.getCode());

        condition.setExistsSettle(req.getExistsSettle());
        condition.setSettleStatus(req.getSettleStatus());

        condition.setApplyDatetimeStart(
            DateUtil.getFrontDate(req.getApplyDatetiemStart(), false));
        condition.setApplyDatetimeEnd(
            DateUtil.getFrontDate(req.getApplyDatetimeEnd(), true));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = "code";
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commodityOrderAO.queryOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629725Req.class);
        ObjValidater.validateReq(req);
    }

}
