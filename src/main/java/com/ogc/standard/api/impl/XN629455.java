package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.dto.req.XN629455Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询寄售
 * @author: silver 
 * @since: Nov 4, 2018 3:28:16 PM 
 * @history:
 */
public class XN629455 extends AProcessor {
    private IDeriveGroupAO deriveGroupAO = SpringContextHolder
        .getBean(IDeriveGroupAO.class);

    private XN629455Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        DeriveGroup condition = new DeriveGroup();
        condition.setOriginalCode(req.getOriginalCode());
        condition.setType(req.getType());
        condition.setCreater(req.getCreater());
        condition.setProductCode(req.getProductCode());
        condition.setStatus(req.getStatus());
        condition.setClaimant(req.getClaimant());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IDeriveGroupAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return deriveGroupAO.queryDeriveGroupPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629455Req.class);
        ObjValidater.validateReq(req);
    }

}
