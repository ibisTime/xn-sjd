package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.dto.req.XN629057Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:02:57 
 * @history:
 */
public class XN629057 extends AProcessor {
    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629057Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GroupAdoptOrder condition = new GroupAdoptOrder();
        condition.setProductCode(req.getProductCode());
        condition.setProductSpecsName(req.getProductSpecsName());
        condition.setStatus(req.getStatus());
        condition.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));

        condition.setApplyDatetime(DateUtil.strToDate(req.getApplyDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGroupAdoptOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return groupAdoptOrderAO.queryGroupAdoptOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629057Req.class);
        ObjValidater.validateReq(req);
    }
}
