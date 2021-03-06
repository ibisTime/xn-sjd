package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Category;
import com.ogc.standard.dto.req.XN629007Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:56:17 
 * @history:
 */
public class XN629007 extends AProcessor {
    private ICategoryAO categoryAO = SpringContextHolder
        .getBean(ICategoryAO.class);

    private XN629007Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Category condition = new Category();
        condition.setParentCode(req.getParentCode());
        condition.setName(req.getName());
        condition.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        condition.setStatus(req.getStatus());
        condition.setLevel(StringValidater.toInteger(req.getLevel()));
        condition.setType(req.getType());
        condition.setTypeList(req.getTypeList());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICategoryAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return categoryAO.queryCategoryList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629007Req.class);
        ObjValidater.validateReq(req);
    }
}
