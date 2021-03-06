package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.dto.req.XN629209Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 产品的认养名单
 * @author: silver 
 * @since: Nov 2, 2018 2:01:16 PM 
 * @history:
 */
public class XN629209 extends AProcessor {

    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629209Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setProductCode(req.getProductCode());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAdoptOrderTreeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return adoptOrderTreeAO.queryProductAdoptedOrder(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629209Req.class);
        ObjValidater.validateReq(req);
    }
}
