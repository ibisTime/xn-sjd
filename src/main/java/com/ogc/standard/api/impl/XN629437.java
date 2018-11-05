package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.OriginalGroup;
import com.ogc.standard.dto.req.XN629437Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询资产
 * @author: silver 
 * @since: Nov 4, 2018 3:28:16 PM 
 * @history:
 */
public class XN629437 extends AProcessor {
    private IOriginalGroupAO originalGroupAO = SpringContextHolder
        .getBean(IOriginalGroupAO.class);

    private XN629437Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        OriginalGroup condition = new OriginalGroup();
        condition.setProductCode(req.getProductCode());
        condition.setOrderCode(req.getOrderCode());
        condition.setOwnerId(req.getOwnerId());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IOriginalGroupAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return originalGroupAO.queryOriginalGroupList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629437Req.class);
        ObjValidater.validateReq(req);
    }

}
