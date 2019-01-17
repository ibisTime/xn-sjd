package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629016Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改产品分类
 * @author: silver 
 * @since: Jan 17, 2019 2:22:04 PM 
 * @history:
 */
public class XN629016 extends AProcessor {
    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629016Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        productAO.editProductCategory(req.getCode(),
            req.getParentCategoryCode(), req.getCategoryCode(),
            req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629016Req.class);
        ObjValidater.validateReq(req);
    }
}
