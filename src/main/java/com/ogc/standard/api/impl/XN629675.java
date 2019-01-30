package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IOfficialSealAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.OfficialSeal;
import com.ogc.standard.dto.req.XN629675Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改公章
 * @author: silver 
 * @since: Jan 17, 2019 3:45:34 PM 
 * @history:
 */
public class XN629675 extends AProcessor {
    private IOfficialSealAO officialSealAO = SpringContextHolder
        .getBean(IOfficialSealAO.class);

    private XN629675Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OfficialSeal condition = new OfficialSeal();
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        condition.setDepartment(req.getDepartment());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOfficialSealAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return officialSealAO.queryOfficialSealPage(start, limit, condition);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629675Req.class);
        ObjValidater.validateReq(req);
    }
}
