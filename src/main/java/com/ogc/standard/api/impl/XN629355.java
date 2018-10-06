package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CarbonBubbleOrder;
import com.ogc.standard.dto.req.XN629355Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询碳泡泡产生订单
 * @author: jiafr 
 * @since: 2018年10月2日 下午2:01:46 
 * @history:
 */
public class XN629355 extends AProcessor {

    private ICarbonBubbleOrderAO carbonBubbleOrderAO = SpringContextHolder
        .getBean(ICarbonBubbleOrderAO.class);

    private XN629355Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CarbonBubbleOrder condition = new CarbonBubbleOrder();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setAdoptUserId(req.getAdoptUserId());
        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(
            req.getCreateDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setStatus(req.getStatus());
        condition.setTaker(req.getTaker());
        condition.setTakeDatetimeStart(DateUtil.strToDate(
            req.getTakeDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setTakeDatetimeEnd(DateUtil
            .strToDate(req.getTakeDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICarbonBubbleOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return carbonBubbleOrderAO.queryCarbonBubbleOrderPage(start, limit,
            condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629355Req.class);
        ObjValidater.validateReq(req);
    }
}
