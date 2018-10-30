package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Product;
import com.ogc.standard.dto.req.XN629027Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询认养产品
 * @author: silver 
 * @since: 2018年9月27日 上午10:52:14 
 * @history:
 */
public class XN629027 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629027Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Product condition = new Product();
        condition.setName(req.getName());
        condition.setParentCategoryCode(req.getParentCategoryCode());
        condition.setCategoryCode(req.getCategoryCode());
        condition.setOwnerId(req.getOwnerId());
        condition.setSellType(req.getSellType());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IProductAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return productAO.queryProductList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629027Req.class);
        ObjValidater.validateReq(req);
    }
}
