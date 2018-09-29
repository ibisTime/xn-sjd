package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.dto.req.XN629047Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询个人定向捐赠认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:02:57 
 * @history:
 */
public class XN629047 extends AProcessor {
    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private XN629047Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrder condition = new AdoptOrder();
        condition.setType(req.getType());
        condition.setProductCode(req.getProductCode());
        condition.setProductSpecsName(req.getProductSpecsName());
        condition.setStatus(req.getStatus());
        condition.setStartDatetimeStart(DateUtil.strToDate(
            req.getStartDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setStartDatetimeEnd(DateUtil.strToDate(
            req.getStartDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetimeStart(DateUtil.strToDate(
            req.getEndDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetimeEnd(DateUtil.strToDate(req.getEndDatetimeEnd(),
            DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAdoptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return adoptOrderAO.queryAdoptOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629047Req.class);
        ObjValidater.validateReq(req);
    }
}
