package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IPresellLogisticsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.PresellLogistics;
import com.ogc.standard.dto.req.XN629467Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询物流单
 * @author: silver 
 * @since: Nov 4, 2018 3:28:16 PM 
 * @history:
 */
public class XN629465 extends AProcessor {
    private IPresellLogisticsAO presellLogisticsAO = SpringContextHolder
        .getBean(IPresellLogisticsAO.class);

    private XN629467Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        PresellLogistics condition = new PresellLogistics();
        condition.setOriginalGroupCode(req.getOriginalGroupCode());
        condition.setLogisticsCompany(req.getLogisticsCompany());
        condition.setLogisticsNumber(req.getLogisticsNumber());
        condition.setDeliver(req.getDeliver());
        condition.setReceiver(req.getReceiver());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IPresellLogisticsAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return presellLogisticsAO.queryPresellLogisticsList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629467Req.class);
        ObjValidater.validateReq(req);
    }

}
